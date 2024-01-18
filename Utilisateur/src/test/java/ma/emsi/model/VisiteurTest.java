package ma.emsi.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class VisiteurTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenEmailIsNotBlank_thenNoConstraintViolations() {
        Visiteur visiteur = new Visiteur("John Doe", "johndoe@example.com");
        Set<ConstraintViolation<Visiteur>> violations = validator.validate(visiteur);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void whenEmailIsBlank_thenConstraintViolation() {
        Visiteur visiteur = new Visiteur("John Doe", "");
        Set<ConstraintViolation<Visiteur>> violations = validator.validate(visiteur);
        Assert.assertFalse(violations.isEmpty());
        for (ConstraintViolation<Visiteur> violation : violations) {
            Assert.assertEquals("Email ne peut pas etre vide", violation.getMessage());
        }
    }

    // You can add more tests for different scenarios if needed.
}
