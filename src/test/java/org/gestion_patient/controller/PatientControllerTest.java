package org.gestion_patient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.gestion_patient.crypto.Crypto;
import org.gestion_patient.entity.*;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.mapper.PatientMapper;
import org.gestion_patient.repository.*;
import org.gestion_patient.security.AppUserDetailService;
import org.gestion_patient.security.SecurityConfig;
import org.gestion_patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private LieuRepository lieuRepository;

    @MockBean
    private ProfessionRepository professionRepository;

    @MockBean
    private MedecintraitantRepository medecintraitantRepository;

    private AppUserRepository appUserRepository;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private PersonneRepository personneRepository;

    @MockBean
    private TypePatientRepository typePatientRepository;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AppUserDetailService appUserDetailService;

    @Test
    public void createPatient() throws Exception {
        // Arrange common data
        Personne mededinIdentite = new Personne(1, "Dupont", "Jean", null, null);
        Lieu marseille = new Lieu(1, "Marseille", "13100");
        Lieu caen = new Lieu(2, "Caen", "14000");
        Medecintraitant med = new Medecintraitant(1, mededinIdentite, marseille);
        int idPraticien = 1;

        // Common mocks
        when(lieuRepository.findByNomVilleAndCodePostal("Marseille", "13100")).thenReturn(marseille);
        when(professionRepository.findByLibelleProfession("Ingénieur")).thenReturn(new Profession(1, "Ingénieur"));
        when(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal("Dupont", "Jean", "Marseille", "13100")).thenReturn(med);

        // Test Case 1: All fields provided
        PatientDto patientFull = new PatientDto();
        patientFull.setDateNaissance("01/01/1980");
        patientFull.setNomVille("Marseille");
        patientFull.setCodePostal("13100");
        patientFull.setNomGenre("Homme");
        patientFull.setNomProfession("Ingénieur");
        patientFull.setNomTypePatient("Adulte");
        patientFull.setNomMedecinTraitant("Dupont");
        patientFull.setPrenomMedecinTraitant("Jean");
        patientFull.setVilleMedecinTraitant("Marseille");
        patientFull.setNomPatient("Martin");
        patientFull.setPrenomPatient("Pierre");
        patientFull.setEmail("pierre.martin@example.com");
        patientFull.setTel("0601020304");
        patientFull.setIdPraticien(idPraticien);

        PatientDto savedPatientFull = new PatientDto();
        savedPatientFull.setIdPatient(1);
        savedPatientFull.setDateNaissance("01/01/1980");
        savedPatientFull.setNomVille("Marseille");
        savedPatientFull.setCodePostal("13100");
        savedPatientFull.setNomGenre("Homme");
        savedPatientFull.setNomProfession("Ingénieur");
        savedPatientFull.setNomTypePatient("Adulte");
        savedPatientFull.setNomMedecinTraitant("Dupont");
        savedPatientFull.setPrenomMedecinTraitant("Jean");
        savedPatientFull.setVilleMedecinTraitant("Marseille");
        savedPatientFull.setNomPatient("Martin");
        savedPatientFull.setPrenomPatient("Pierre");
        savedPatientFull.setEmail("pierre.martin@example.com");
        savedPatientFull.setTel("0601020304");
        savedPatientFull.setIdPraticien(1);


        when(patientService.createPatient(any(PatientDto.class), eq(idPraticien))).thenReturn(savedPatientFull);

        mockMvc.perform(post("/api/patient/{idPraticien}", idPraticien)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientFull)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPatient", is(1)))
                .andExpect(jsonPath("$.nomVille", is("Marseille")))
                .andExpect(jsonPath("$.codePostal", is("13100")))
                .andExpect(jsonPath("$.nomGenre", is("Homme")))
                .andExpect(jsonPath("$.nomProfession", is("Ingénieur")))
                .andExpect(jsonPath("$.nomTypePatient", is("Adulte")))
                .andExpect(jsonPath("$.nomMedecinTraitant", is("Dupont")))
                .andExpect(jsonPath("$.prenomMedecinTraitant", is("Jean")))
                .andExpect(jsonPath("$.villeMedecinTraitant", is("Marseille")))
                .andExpect(jsonPath("$.nomPatient", is("Martin")))
                .andExpect(jsonPath("$.prenomPatient", is("Pierre")))
                .andExpect(jsonPath("$.email", is("pierre.martin@example.com")))
                .andExpect(jsonPath("$.tel", is("0601020304")))
                .andExpect(jsonPath("$.idPraticien", is(1)));

        // Test Case 2: Missing optional fields
        PatientDto patientPartial = new PatientDto();
        patientPartial.setDateNaissance("01/01/1980");
        patientPartial.setNomGenre("Homme");
        patientPartial.setNomTypePatient("Adulte");
        patientPartial.setNomPatient("Lejeune");
        patientPartial.setPrenomPatient("Olivier");
        patientPartial.setTel("0601020304");
        patientPartial.setIdPraticien(idPraticien);

        PatientDto savedPatientPartial = new PatientDto();
        savedPatientPartial.setIdPatient(2);
        savedPatientPartial.setDateNaissance("01/01/1980");
        savedPatientPartial.setNomGenre("Homme");
        savedPatientPartial.setNomTypePatient("Adulte");
        savedPatientPartial.setNomPatient("Lejeune");
        patientPartial.setCodePostal("14000");
        patientPartial.setNomVille("Caen");
        savedPatientPartial.setPrenomPatient("Olivier");
        savedPatientPartial.setTel("0601020304");
        savedPatientPartial.setIdPraticien(1);

        when(patientService.createPatient(any(PatientDto.class), eq(idPraticien))).thenReturn(savedPatientPartial);

        mockMvc.perform(post("/api/patient/{idPraticien}", idPraticien)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientPartial)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPatient", is(2)))
                .andExpect(jsonPath("$.nomPatient", is("Lejeune")))
                .andExpect(jsonPath("$.prenomPatient", is("Olivier")))
                .andExpect(jsonPath("$.nomProfession").doesNotExist())
                .andExpect(jsonPath("$.nomMedecinTraitant").doesNotExist())
                .andExpect(jsonPath("$.prenomMedecinTraitant").doesNotExist())
                .andExpect(jsonPath("$.villeMedecinTraitant").doesNotExist())
                .andExpect(jsonPath("$.email").doesNotExist())
                .andExpect(jsonPath("$.tel", is("0601020304")))
                .andExpect(jsonPath("$.idPraticien", is(1)));


        // Test Case 3: Creating a new patient with a new city
        PatientDto patientWithNewCity = new PatientDto();
        patientWithNewCity.setDateNaissance("01/01/1980");
        patientWithNewCity.setNomVille("Caen");
        patientWithNewCity.setCodePostal("14000");
        patientWithNewCity.setNomGenre("Homme");
        patientWithNewCity.setNomProfession("Ingénieur");
        patientWithNewCity.setNomTypePatient("Adulte");
        patientWithNewCity.setNomPatient("Lenoir");
        patientWithNewCity.setPrenomPatient("Maxime");
        patientWithNewCity.setEmail("max@example.com");
        patientWithNewCity.setTel("0601020304");
        patientWithNewCity.setIdPraticien(idPraticien);

        PatientDto savedPatientWithNewCity = new PatientDto();
        savedPatientWithNewCity.setIdPatient(3);
        savedPatientWithNewCity.setDateNaissance("01/01/1980");
        savedPatientWithNewCity.setNomVille("Caen");
        savedPatientWithNewCity.setCodePostal("14000");
        savedPatientWithNewCity.setNomGenre("Homme");
        savedPatientWithNewCity.setNomProfession("Ingénieur");
        savedPatientWithNewCity.setNomTypePatient("Adulte");
        savedPatientWithNewCity.setNomPatient("Lenoir");
        savedPatientWithNewCity.setPrenomPatient("Maxime");
        savedPatientWithNewCity.setEmail("max@example.com");
        savedPatientWithNewCity.setTel("0601020304");
        savedPatientWithNewCity.setIdPraticien(idPraticien);

        when(lieuRepository.findByNomVilleAndCodePostal("Caen", "14000")).thenReturn(null);
        when(lieuRepository.save(any(Lieu.class))).thenReturn(caen);
        when(patientService.createPatient(any(PatientDto.class), eq(idPraticien))).thenReturn(savedPatientWithNewCity);

        mockMvc.perform(post("/api/patient/{idPraticien}", idPraticien)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientWithNewCity)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPatient", is(3)))
                .andExpect(jsonPath("$.nomVille", is("Caen")))
                .andExpect(jsonPath("$.codePostal", is("14000")))
                .andExpect(jsonPath("$.nomGenre", is("Homme")))
                .andExpect(jsonPath("$.nomProfession", is("Ingénieur")))
                .andExpect(jsonPath("$.nomTypePatient", is("Adulte")))
                .andExpect(jsonPath("$.nomPatient", is("Lenoir")))
                .andExpect(jsonPath("$.prenomPatient", is("Maxime")))
                .andExpect(jsonPath("$.email", is("max@example.com")))
                .andExpect(jsonPath("$.tel", is("0601020304")));

    }

    @Test
    public void updatePatient() throws Exception {
// Arrange
        int id = 1;
        Patient existingPatient = new Patient();
        existingPatient.setIdPatient(id);
        existingPatient.setDateNaissance(Crypto.cryptService("01/01/1980"));
        Personne existingPersonne = new Personne();
        existingPersonne.setNom(Crypto.cryptService("DUPONT"));
        existingPersonne.setPrenom(Crypto.cryptService("JEAN"));
        existingPersonne.setTel(Crypto.cryptService("0601020304"));
        existingPersonne.setEmail(Crypto.cryptService("jean.dupont@example.com"));
        existingPatient.setIdentite(existingPersonne);
        existingPatient.setGenre(new Genre(1, "Homme"));
        existingPatient.setTypePatient(new TypePatient(1, "Adulte"));
        existingPatient.setVille(new Lieu(1, "MARSEILLE", "13000"));
        existingPatient.setProfession(new Profession(1, "INGÉNIEUR"));

        PatientDto updatedPatientDto = new PatientDto();
        updatedPatientDto.setNomPatient("MARTIN");
        updatedPatientDto.setPrenomPatient("PIERRE");
        updatedPatientDto.setTel("0708091011");
        updatedPatientDto.setEmail("pierre.martin@example.com");
        updatedPatientDto.setDateNaissance("02/02/1990");
        updatedPatientDto.setNomGenre("Femme");
        updatedPatientDto.setNomTypePatient("Enfant");
        updatedPatientDto.setNomVille("CAEN");
        updatedPatientDto.setCodePostal("14000");
        updatedPatientDto.setNomProfession("DOCTEUR");
        updatedPatientDto.setNomMedecinTraitant("SMITH");
        updatedPatientDto.setPrenomMedecinTraitant("JOHN");
        updatedPatientDto.setVilleMedecinTraitant("PARIS");
        updatedPatientDto.setCodePostalMedecinTraitant("75000");

        // Patient après mise à jour
        Patient updatedPatient = new Patient();
        updatedPatient.setIdPatient(id);
        updatedPatient.setDateNaissance(Crypto.cryptService("02/02/1990"));
        Personne updatedPersonne = new Personne();
        updatedPersonne.setNom(Crypto.cryptService("MARTIN"));
        updatedPersonne.setPrenom(Crypto.cryptService("PIERRE"));
        updatedPersonne.setTel(Crypto.cryptService("0708091011"));
        updatedPersonne.setEmail(Crypto.cryptService("pierre.martin@example.com"));
        updatedPatient.setIdentite(updatedPersonne);
        updatedPatient.setGenre(new Genre(2, "Femme"));
        updatedPatient.setTypePatient(new TypePatient(2, "Enfant"));
        updatedPatient.setVille(new Lieu(2, "CAEN", "14000"));
        updatedPatient.setProfession(new Profession(2, "DOCTEUR"));
        Medecintraitant updatedMedecinTraitant = new Medecintraitant();
        updatedMedecinTraitant.setIdentiteDoc(new Personne(2, Crypto.cryptService("SMITH"), Crypto.cryptService("JOHN"), null, null));
        updatedMedecinTraitant.setLieu(new Lieu(3, "PARIS", "75000"));
        updatedPatient.setMedecinTraitant(updatedMedecinTraitant);

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(personneRepository.save(any(Personne.class))).thenReturn(updatedPersonne);
        when(genreRepository.findByNomGenre("Femme")).thenReturn(new Genre(2, "Femme"));
        when(typePatientRepository.findByNomTypePatient("Enfant")).thenReturn(new TypePatient(2, "Enfant"));
        when(lieuRepository.findByNomVilleAndCodePostal("CAEN", "14000")).thenReturn(null);
        when(lieuRepository.save(any(Lieu.class))).thenReturn(new Lieu(2, "CAEN", "14000"));
        when(professionRepository.findByLibelleProfession("DOCTEUR")).thenReturn(null);
        when(professionRepository.save(any(Profession.class))).thenReturn(new Profession(2, "DOCTEUR"));
        when(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal(
                Crypto.cryptService("SMITH"), Crypto.cryptService("JOHN"), "PARIS", "75000"))
                .thenReturn(updatedMedecinTraitant);
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);
        when(patientService.updatePatient(eq(id),any(PatientDto.class) )).thenReturn(updatedPatientDto);

        mockMvc.perform(put("/api/patient/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPatientDto)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.tel").value("0708091011"))
                .andExpect(jsonPath("$.email").value("pierre.martin@example.com"))
                .andExpect(jsonPath("$.dateNaissance").value("02/02/1990"))
                .andExpect(jsonPath("$.nomGenre").value("Femme"))
                .andExpect(jsonPath("$.nomTypePatient").value("Enfant"))
                .andExpect(jsonPath("$.nomVille").value("CAEN"))
                .andExpect(jsonPath("$.codePostal").value("14000"))
                .andExpect(jsonPath("$.nomProfession").value("DOCTEUR"))
                .andExpect(jsonPath("$.nomMedecinTraitant").value("SMITH"))
                .andExpect(jsonPath("$.prenomMedecinTraitant").value("JOHN"))
                .andExpect(jsonPath("$.villeMedecinTraitant").value("PARIS"))
                .andExpect(jsonPath("$.nomPatient").value("MARTIN"))
                .andExpect(jsonPath("$.prenomPatient").value("PIERRE"))
                .andExpect(jsonPath("$.codePostalMedecinTraitant").value("75000"));
    }

    }



