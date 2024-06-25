package org.gestion_patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gestion_patient.entityDto.MedecintraitantDto;
import org.gestion_patient.security.PraticienDetailService;
import org.gestion_patient.security.SecurityConfig;
import org.gestion_patient.service.MedecinTraitantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MedecinTraitantController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class MedecinTraitantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedecinTraitantService medecinService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PraticienDetailService praticienDetailService;


    @Test
    public void createMedecintraitant () throws Exception {
        // Arrange
        MedecintraitantDto medecin = new MedecintraitantDto();
        medecin.setPrenomMedecinTraitant("Laurent");
        medecin.setNomMedecinTraitant("Lamotte");
        medecin.setVille("Roubaix");

        // Simuler la réponse de la méthode create du service
        MedecintraitantDto savedMedecinDto = new MedecintraitantDto();
        savedMedecinDto.setIdMedecinTraitant(1);
        savedMedecinDto.setPrenomMedecinTraitant("Laurent");
        savedMedecinDto.setNomMedecinTraitant("Lamotte");
        savedMedecinDto.setVille("Roubaix");

        when(medecinService.createMedecintraitant(any(MedecintraitantDto.class))).thenReturn(savedMedecinDto);

        mockMvc.perform(post("/api/medecintraitant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medecin)))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.idMedecinTraitant", is(1))))
                .andExpect((jsonPath("$.nomMedecinTraitant", is("Lamotte"))))
                .andExpect((jsonPath("$.prenomMedecinTraitant", is("Laurent"))))
                .andExpect((jsonPath("$.ville", is("Roubaix"))));



    }

    @Test
    public void updateMedecinTraitant() throws Exception {
        // Arrange
        int id = 1;

        MedecintraitantDto medecinDto = new MedecintraitantDto();
        medecinDto.setIdMedecinTraitant(id);
        medecinDto.setPrenomMedecinTraitant("Samuel");
        medecinDto.setNomMedecinTraitant("Vercot");
        medecinDto.setVille("Lille");

        // Simuler la réponse de la méthode update du service
        MedecintraitantDto updatedMedecinDto = new MedecintraitantDto();
        updatedMedecinDto.setIdMedecinTraitant(1);
        updatedMedecinDto.setPrenomMedecinTraitant("Laurent");
        updatedMedecinDto.setNomMedecinTraitant("Lamotte");
        updatedMedecinDto.setVille("Roubaix");

        when(medecinService.updateMedecinTraintant(any(MedecintraitantDto.class),eq(id))).thenReturn(updatedMedecinDto);


        mockMvc.perform(put("/api/medecintraitant/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medecinDto)))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.idMedecinTraitant", is(1))))
                .andExpect((jsonPath("$.nomMedecinTraitant", is("Lamotte"))))
                .andExpect((jsonPath("$.prenomMedecinTraitant", is("Laurent"))))
                .andExpect((jsonPath("$.ville", is("Roubaix"))));



    }

    @Test
    public void getMedecintraitantById() throws Exception {
        int id = 1;

        MedecintraitantDto medecinDto = new MedecintraitantDto();
        medecinDto.setIdMedecinTraitant(id);
        medecinDto.setPrenomMedecinTraitant("Laurent");
        medecinDto.setNomMedecinTraitant("Lamotte");
        medecinDto.setVille("Roubaix");

        // Simuler la réponse de la méthode create du service
        MedecintraitantDto targetedMedecinDto = new MedecintraitantDto();
        targetedMedecinDto.setIdMedecinTraitant(1);
        targetedMedecinDto.setPrenomMedecinTraitant("Laurent");
        targetedMedecinDto.setNomMedecinTraitant("Lamotte");
        targetedMedecinDto.setVille("Roubaix");

        when(medecinService.findMedecintraitantById(eq(id))).thenReturn(targetedMedecinDto);

        mockMvc.perform(get("/api/medecintraitant/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.idMedecinTraitant", is(1))))
                .andExpect((jsonPath("$.nomMedecinTraitant", is("Lamotte"))))
                .andExpect((jsonPath("$.prenomMedecinTraitant", is("Laurent"))))
                .andExpect((jsonPath("$.ville", is("Roubaix"))));


    }

    @Test
    public void getAllLieu() throws Exception {
        // Arrange
        MedecintraitantDto medecinDto1 = new MedecintraitantDto();
        medecinDto1.setIdMedecinTraitant(1);
        medecinDto1.setPrenomMedecinTraitant("Laurent");
        medecinDto1.setNomMedecinTraitant("Lamotte");
        medecinDto1.setVille("Roubaix");

        MedecintraitantDto medecinDto2 = new MedecintraitantDto();
        medecinDto2.setIdMedecinTraitant(2);
        medecinDto2.setPrenomMedecinTraitant("Bernard");
        medecinDto2.setNomMedecinTraitant("Pavot");
        medecinDto2.setVille("Tourcoing");

        List<MedecintraitantDto> allMedecins = Arrays.asList(medecinDto1, medecinDto2);

        when(medecinService.getAll()).thenReturn(allMedecins);

        // Act and Assert
        mockMvc.perform(get("/api/medecintraitant/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idMedecinTraitant", is(1)))
                .andExpect(jsonPath("$[0].nomMedecinTraitant", is("Lamotte")))
                .andExpect(jsonPath("$[0].prenomMedecinTraitant", is("Laurent")))
                .andExpect(jsonPath("$[0].ville", is("Roubaix")))
                .andExpect(jsonPath("$[1].idMedecinTraitant", is(2)))
                .andExpect(jsonPath("$[1].nomMedecinTraitant", is("Pavot")))
                .andExpect(jsonPath("$[1].prenomMedecinTraitant", is("Bernard")))
                .andExpect(jsonPath("$[1].ville", is("Tourcoing")));
    }



}

