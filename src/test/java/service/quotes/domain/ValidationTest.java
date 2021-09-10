package service.quotes.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {
    private Validator validator;
    private Set<ConstraintViolation<Quote>> violations;
    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    //bid > ask
    @Test
    public void quoteValidationTest() {
        Quote quote = new Quote("RU00A0JX0J22", 104.1, 103.2);
        violations = validator.validate(quote);
        String mes = "Bid must be less than ask";
        Optional<ConstraintViolation<Quote>> optionConstraint = violations.stream().findFirst();
        optionConstraint.ifPresent(constraint -> assertEquals(mes, constraint.getMessage()));
        assertTrue(optionConstraint.isPresent());
        assertEquals(1,violations.size());
    }

    //isin != 12
    @Test
    public void quoteValidationTest2() {
        Quote quote = new Quote("RU00A0JX0J2", 100.1, 103.2);
        violations = validator.validate(quote);
        String mes = "Size must be 12";
        Optional<ConstraintViolation<Quote>> optionConstraint = violations.stream().findFirst();
        optionConstraint.ifPresent(constraint -> assertEquals(mes, constraint.getMessage()));
        assertTrue(optionConstraint.isPresent());
        assertEquals(1,violations.size());
    }

    //isin == null
    @Test
    public void quoteValidationTest3() {
        Quote quote = new Quote(null, 100.1, 102.3);
        violations = validator.validate(quote);
        String mes = "Isin must not be null";
        Optional<ConstraintViolation<Quote>> optionConstraint = violations.stream().findFirst();
        optionConstraint.ifPresent(constraint -> assertEquals(mes, constraint.getMessage()));
        assertTrue(optionConstraint.isPresent());
        assertEquals(1,violations.size());
    }

    //ask == null
    @Test
    public void quoteValidationTest4() {
        Quote quote = new Quote("RU00A0JX0J22", 100.1,  null);
        violations = validator.validate(quote);
        String mes = "<ask> must not be null";
        Optional<ConstraintViolation<Quote>> optionConstraint = violations.stream().findFirst();
        optionConstraint.ifPresent(constraint -> assertEquals(mes, constraint.getMessage()));
        assertTrue(optionConstraint.isPresent());
        assertEquals(1,violations.size());
    }
}
