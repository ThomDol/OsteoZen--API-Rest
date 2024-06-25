package org.gestion_patient.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.gestion_patient.entityDto.LieuDto;
import org.gestion_patient.security.PraticienDetailService;
import org.gestion_patient.security.SecurityConfig;
import org.gestion_patient.service.LieuService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LieuController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class LieuControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LieuService lieuService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PraticienDetailService praticienDetailService;


    @Test
    public void createLieu () throws Exception {
        // Arrange
        LieuDto lieuDto = new LieuDto();
        lieuDto.setNomville("Marseille");
        lieuDto.setCodePostal("13100");

        // Simuler la réponse de la méthode create du service
        LieuDto savedLieuDto = new LieuDto();
        savedLieuDto.setIdVille(1);
        savedLieuDto.setNomville("Marseille");
        savedLieuDto.setCodePostal("13100");

        when(lieuService.createLieu(any(LieuDto.class))).thenReturn(savedLieuDto);

        mockMvc.perform(post("/api/lieu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lieuDto)))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.idVille", is(1))))
                .andExpect((jsonPath("$.nomville", is("Marseille"))))
                .andExpect((jsonPath("$.codePostal", is("13100"))));



    }

    @Test
    public void updateLieu() throws Exception {
        // Arrange
        int id = 1;

        LieuDto lieuDto = new LieuDto();
        lieuDto.setIdVille(id);
        lieuDto.setNomville("Arras");
        lieuDto.setCodePostal("62000");

        LieuDto lieuDtoUpadted = new LieuDto();
        lieuDtoUpadted.setNomville("Arras");
        lieuDtoUpadted.setCodePostal("62000");

        // Simuler la réponse de la méthode create du service
        LieuDto updatedLieuDto = new LieuDto();
        updatedLieuDto.setIdVille(1);
        updatedLieuDto.setNomville("Arras");
        updatedLieuDto.setCodePostal("62000");

        when(lieuService.updateLieu(eq(id), any(LieuDto.class))).thenReturn(updatedLieuDto);


        mockMvc.perform(put("/api/lieu/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lieuDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVille", is(1)))
                .andExpect(jsonPath("$.nomville", is("Arras")))
                .andExpect(jsonPath("$.codePostal", is("62000")));



    }

    @Test
    public void getLieuById() throws Exception {
        int id = 1;

        LieuDto lieuDto = new LieuDto();
        lieuDto.setIdVille(id);
        lieuDto.setNomville("Arras");
        lieuDto.setCodePostal("62000");

        // Simuler la réponse de la méthode create du service
        LieuDto targetedLieuDto = new LieuDto();
        targetedLieuDto.setIdVille(1);
        targetedLieuDto.setNomville("Arras");
        targetedLieuDto.setCodePostal("62000");

        when(lieuService.getLieuById(eq(id))).thenReturn(targetedLieuDto);

        mockMvc.perform(get("/api/lieu/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVille", is(id)))  // Assurez-vous que le chemin JSON correspond correctement
                .andExpect(jsonPath("$.nomville", is("Arras")))
                .andExpect(jsonPath("$.codePostal", is("62000")));


    }

    @Test
    public void getAllLieu() throws Exception {
        // Arrange
        LieuDto lieuDto1 = new LieuDto();
        lieuDto1.setIdVille(1);
        lieuDto1.setNomville("Paris");
        lieuDto1.setCodePostal("75000");

        LieuDto lieuDto2 = new LieuDto();
        lieuDto2.setIdVille(2);
        lieuDto2.setNomville("Marseille");
        lieuDto2.setCodePostal("13000");

        List<LieuDto> allLieux = Arrays.asList(lieuDto1, lieuDto2);

        when(lieuService.findAll()).thenReturn(allLieux);

        // Act and Assert
        mockMvc.perform(get("/api/lieu/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idVille", is(1)))
                .andExpect(jsonPath("$[0].nomville", is("Paris")))
                .andExpect(jsonPath("$[0].codePostal", is("75000")))
                .andExpect(jsonPath("$[1].idVille", is(2)))
                .andExpect(jsonPath("$[1].nomville", is("Marseille")))
                .andExpect(jsonPath("$[1].codePostal", is("13000")));
    }



}
