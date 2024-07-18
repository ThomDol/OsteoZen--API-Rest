package org.gestion_patient.service;

import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.exception.RessourceAlreadyexistsException;
import org.gestion_patient.repository.LieuRepository;
import org.gestion_patient.repository.MedecintraitantRepository;
import org.gestion_patient.repository.PersonneRepository;
import org.gestion_patient.service.impl.MedecinTraitantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MedecinTraitantServiceImplTest {

    @MockBean
    private MedecintraitantRepository medecintraitantRepository;

    @MockBean
    private PersonneRepository personneRepository;

    @MockBean
    private LieuRepository lieuRepository;

    @InjectMocks
    private MedecinTraitantServiceImpl medecintraitantService;

    private MedecintraitantDto medecintraitantDto;

    @BeforeEach
    public void setUp() {
        medecintraitantDto = new MedecintraitantDto();
        medecintraitantDto.setNomMedecinTraitant("Dupont");
        medecintraitantDto.setPrenomMedecinTraitant("Jean");
        medecintraitantDto.setVilleMedecinTraitant("Paris");
        medecintraitantDto.setCodePostalMedecinTraitant("75000");
    }

    @Test
    public void testCreateMedecintraitant_MedecinAlreadyExists() throws Exception {
        when(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                anyString(), anyString(), anyString(), anyString())).thenReturn(new Medecintraitant());

        Exception exception = assertThrows(RessourceAlreadyexistsException.class, () -> {
            medecintraitantService.createMedecintraitant(medecintraitantDto);
        });

        assertEquals("This Medecintraitant already exists", exception.getMessage());
    }

    @Test
    public void testCreateMedecintraitant_HomonymExistsInOtherCityWithContact() throws Exception {
        // Arrange: Configure le mock pour retourner null pour le médecin traitant existant
        when(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                anyString(), anyString(), anyString(), anyString())).thenReturn(null);

        // Arrange: Configure le mock pour retourner une personne existante avec un téléphone
        Personne existingPersonne = new Personne();
        existingPersonne.setTel("0102030405");
        when(personneRepository.findByNomAndPrenom(anyString(), anyString())).thenReturn(existingPersonne);

        // Arrange: Configure le mock pour sauvegarder une nouvelle personne
        when(personneRepository.save(any(Personne.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act: Appelle la méthode pour tester
        MedecintraitantDto result = medecintraitantService.createMedecintraitant(medecintraitantDto);

        // Assert: Vérifie que le résultat n'est pas null
        assertNotNull(result);

        // Assert: Vérifie qu'une nouvelle personne a été créée et sauvegardée
        verify(personneRepository, times(1)).save(any(Personne.class));
    }

    @Test
    public void testCreateMedecintraitant_NewMedecintraitant() throws Exception {
        when(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                anyString(), anyString(), anyString(), anyString())).thenReturn(null);

        when(personneRepository.findByNomAndPrenom(anyString(), anyString())).thenReturn(null);

        when(personneRepository.save(any(Personne.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(lieuRepository.findByNomVilleAndCodePostal(anyString(), anyString())).thenReturn(null);
        when(lieuRepository.save(any(Lieu.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(medecintraitantRepository.save(any(Medecintraitant.class))).thenAnswer(invocation -> {
            Medecintraitant medecintraitant = invocation.getArgument(0);
            medecintraitant.setIdMedecinTraitant(1);
            return medecintraitant;
        });

        MedecintraitantDto result = medecintraitantService.createMedecintraitant(medecintraitantDto);

        assertNotNull(result);



        assertEquals("Dupont", Crypto.decryptService(result.getNomMedecinTraitant()));
        assertEquals("Jean", Crypto.decryptService(result.getPrenomMedecinTraitant()));
        assertEquals("Paris", result.getVilleMedecinTraitant());
        assertEquals("75000", result.getCodePostalMedecinTraitant());
    }
}
