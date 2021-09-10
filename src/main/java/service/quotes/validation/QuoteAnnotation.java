package service.quotes.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BidAskQuoteValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface QuoteAnnotation {
    String message() default "Invalid bid and isin";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
