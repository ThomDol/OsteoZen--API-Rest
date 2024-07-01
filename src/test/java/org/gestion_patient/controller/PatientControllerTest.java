package org.gestion_patient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entity.Medecintraitant;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entity.Profession;
import org.gestion_patient.entityDto.PatientDto;
import org.gestion_patient.repository.LieuRepository;
import org.gestion_patient.repository.MedecintraitantRepository;
import org.gestion_patient.repository.ProfessionRepository;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AppUserDetailService appUserDetailService;

    @Test
    public void createPatient () throws Exception {
//Arrange
        Personne mededinIdentite = new Personne(1,"Dupont","Jean",null,null);
        Lieu marseille = new Lieu(1,"Marseille","13100");
        Medecintraitant med = new Medecintraitant(1,mededinIdentite,marseille);

        int idPraticien=1;

        PatientDto patient = new PatientDto();
        patient.setDateNaissance("01/01/1980");
        patient.setNomVille("Marseille");
        patient.setCodePostal("13100");
        patient.setNomGenre("Homme");
        patient.setNomProfession("Ingénieur");
        patient.setNomTypePatient("Adulte");
        patient.setNomMedecinTraitant("Dupont");
        patient.setPrenomMedecinTraitant("Jean");
        patient.setVilleMedecinTraitant("Marseille");
        patient.setNomPatient("Martin");
        patient.setPrenomPatient("Pierre");
        patient.setEmail("pierre.martin@example.com");
        patient.setTel("0601020304");
        patient.setIdPraticien(idPraticien);

// Simuler la réponse de la méthode create du service
        PatientDto savedPatient = new PatientDto();
        savedPatient.setIdPatient(1);
        savedPatient.setDateNaissance("01/01/1980");
        savedPatient.setNomVille("Marseille");
        savedPatient.setCodePostal("13100");
        savedPatient.setNomGenre("Homme");
        savedPatient.setNomProfession("Ingénieur");
        savedPatient.setNomTypePatient("Adulte");
        savedPatient.setNomMedecinTraitant("Dupont");
        savedPatient.setPrenomMedecinTraitant("Jean");
        savedPatient.setVilleMedecinTraitant("Marseille");
        savedPatient.setNomPatient("Martin");
        savedPatient.setPrenomPatient("Pierre");
        savedPatient.setEmail("pierre.martin@example.com");
        savedPatient.setTel("0601020304");
        savedPatient.setIdPraticien(1);

        // Mocking the repositories and service calls
        when(lieuRepository.findByNomVilleAndCodePostal("Marseille", "13100")).thenReturn(marseille);
        when(professionRepository.findByLibelleProfession("Ingénieur")).thenReturn(new Profession(1, "Ingénieur"));
        when(medecintraitantRepository.findByIdentiteDocNomAndIdentiteDocPrenomAndLieuNomVilleAndLieuCodePostal("Dupont", "Jean", "Marseille","13100")).thenReturn(med);
        when(patientService.createPatient(any(PatientDto.class),eq(idPraticien))).thenReturn(savedPatient);

        mockMvc.perform(post("/api/patient/{idPraticien}",idPraticien)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
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



    }

    }


