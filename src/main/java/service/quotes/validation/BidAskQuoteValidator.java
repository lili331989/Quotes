package service.quotes.validation;

import service.quotes.domain.Quote;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BidAskQuoteValidator implements ConstraintValidator<QuoteAnnotation, Quote> {
    public void initialize(QuoteAnnotation constraintAnnotation) {
    }

    public boolean isValid(Quote object, ConstraintValidatorContext context) {
        if (!(object instanceof Quote)) {
            throw new IllegalArgumentException("@Quote only applies to Quote");
        }
        Quote quote = (Quote) object;
        return quote.getBid() == null ? true :  quote.getBid() < quote.getAsk();
    }
}
