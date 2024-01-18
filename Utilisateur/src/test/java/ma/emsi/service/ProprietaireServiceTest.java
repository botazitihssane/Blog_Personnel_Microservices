package ma.emsi.service;

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
import ma.emsi.response.ApiResponse;
import ma.emsi.service.impl.ProprietaireServiceImpl;

public class ProprietaireServiceTest {

    @Mock
    private ProprietaireRepository proprietaireRepository; // Replace with your actual repository

    @InjectMocks
    private ProprietaireServiceImpl proprietaireService; // Your service implementation

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Proprietaire proprietaire = new Proprietaire();
        proprietaireService.create(proprietaire);
        verify(proprietaireRepository, times(1)).save(proprietaire);
    }

    @Test
    public void testUpdate() {
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setId(1);
        when(proprietaireRepository.findById(1)).thenReturn(proprietaire);
        proprietaireService.update(proprietaire);
        verify(proprietaireRepository, times(1)).save(proprietaire);
    }

    @Test
    public void testDelete() {
        int proprietaireId = 1;
        proprietaireService.delete(proprietaireId);
        verify(proprietaireRepository, times(1)).deleteById(proprietaireId);
    }

    @Test
    public void testFindAll() {
        Proprietaire proprietaire1 = new Proprietaire();
        Proprietaire proprietaire2 = new Proprietaire();
        when(proprietaireRepository.findAll()).thenReturn(Arrays.asList(proprietaire1, proprietaire2));

        List<Proprietaire> result = proprietaireService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Proprietaire proprietaire = new Proprietaire();
        when(proprietaireRepository.findById(1)).thenReturn(proprietaire);

        Proprietaire result = proprietaireService.findById(1);
        assertNotNull(result);
    }

 
   
    
}
