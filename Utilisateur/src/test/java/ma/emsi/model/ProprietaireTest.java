package ma.emsi.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProprietaireTest {

    private Proprietaire proprietaire;

    @Before
    public void setUp() {
        proprietaire = new Proprietaire(1, "test@example.com", "password123", "photoUrl", "biography");
    }

    @Test
    public void testConstructorWithArguments() {
        assertEquals(1, proprietaire.getId());
        assertEquals("test@example.com", proprietaire.getEmail());
        assertEquals("password123", proprietaire.getPassword());
        assertEquals("photoUrl", proprietaire.getPhoto());
        assertEquals("biography", proprietaire.getBiographie());
    }

    @Test
    public void testSetPhoto() {
        String newPhoto = "newPhotoUrl";
        proprietaire.setPhoto(newPhoto);
        assertEquals(newPhoto, proprietaire.getPhoto());
    }

    @Test
    public void testSetBiographie() {
        String newBiographie = "newBiography";
        proprietaire.setBiographie(newBiographie);
        assertEquals(newBiographie, proprietaire.getBiographie());
    }

    // Tests for other constructors, getters, and setters...
    // For constructors, you can test for expected exceptions if the input doesn't meet the validation constraints.
    // For example, you can use the @Test(expected = ConstraintViolationException::class) annotation
    // if you're using Bean Validation API for your validations.

}
