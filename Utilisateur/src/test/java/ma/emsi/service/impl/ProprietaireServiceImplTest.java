package ma.emsi.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ma.emsi.model.Proprietaire;
import ma.emsi.model.Utilisateur;
import ma.emsi.repository.ProprietaireRepository;
import ma.emsi.repository.UtilisateurRepository;
import ma.emsi.response.ApiResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ProprietaireServiceImplTest {

    @Mock
    private ProprietaireRepository proprietaireRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ProprietaireServiceImpl proprietaireService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate_NewEmail() {
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setEmail("newemail@example.com");
        proprietaire.setPassword("password");

        when(utilisateurRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        proprietaireService.create(proprietaire);

        verify(proprietaireRepository, times(1)).save(proprietaire);
    }

    @Test(expected = RuntimeException.class)
    public void testCreate_ExistingEmail() {
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setEmail("existingemail@example.com");
        proprietaire.setPassword("password");

        when(utilisateurRepository.existsByEmail(anyString())).thenReturn(true);

        proprietaireService.create(proprietaire);
    }

    // Similar structure for update, delete, findAll, findById, login

    // Example for testing login
    @Test
    public void testLogin_Success() {
        String email = "user@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";

        Utilisateur user = new Utilisateur();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        when(utilisateurRepository.getUserByEmail(email)).thenReturn(user);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(utilisateurRepository.findByEmailAndPassword(email, encodedPassword)).thenReturn(Optional.of(user));

        ApiResponse result = proprietaireService.login(new Utilisateur(email, password));

        assertEquals("Login Success", result.getMessage());
        assertTrue(result.getStatus()); // Use getStatus() instead of isSuccess() or getSuccess()


    // Add more tests for different scenarios
}
}
