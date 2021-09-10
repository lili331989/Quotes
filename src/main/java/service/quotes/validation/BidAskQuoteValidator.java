package service.quotes.validation;

import service.quotes.domain.Quote;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BidAskQuoteValidator implements ConstraintValidator<QuoteAnnotation, Quote> {
    public void initialize(QuoteAnnotation constraintAnnotation) {
    }

    public boolean isValid(Quote quote, ConstraintValidatorContext context) {
        if (quote == null) {
            throw new IllegalArgumentException("Quote null");
        }
        return quote.getBid() == null || quote.getBid() < quote.getAsk();
    }
}
