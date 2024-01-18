package ma.emsi.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class UtilisateurTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenEmailIsNotBlank_thenNoConstraintViolations() {
        Utilisateur utilisateur = new Utilisateur("user@example.com", "password123");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void whenEmailIsBlank_thenConstraintViolation() {
        Utilisateur utilisateur = new Utilisateur("", "password123");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        Assert.assertFalse(violations.isEmpty());
        for (ConstraintViolation<Utilisateur> violation : violations) {
            Assert.assertEquals("L'e-mail ne peut pas être vide", violation.getMessage());
        }
    }

    @Test
    public void whenPasswordLengthIsLessThan8_thenConstraintViolation() {
        Utilisateur utilisateur = new Utilisateur("user@example.com", "pass");
        Set<ConstraintViolation<Utilisateur>> violations = validator.validate(utilisateur);
        Assert.assertFalse(violations.isEmpty());
        for (ConstraintViolation<Utilisateur> violation : violations) {
            Assert.assertEquals("Le mot de passe doit avoir au moins 8 caractères", violation.getMessage());
        }
    }

    // Add more tests for different scenarios and other constraints if needed.
}
