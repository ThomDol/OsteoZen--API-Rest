package org.gestion_patient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.gestion_patient.entity.Lieu;
import org.gestion_patient.entity.Personne;
import org.gestion_patient.entity.Role;
import org.gestion_patient.entityDto.AppUserDto;
import org.gestion_patient.repository.*;
import org.gestion_patient.security.AppUserDetailService;
import org.gestion_patient.security.SecurityConfig;
import org.gestion_patient.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppUserController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class AppUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private LieuRepository lieuRepository;

    @MockBean
    private PersonneRepository personneRepository;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AppUserDetailService appUserDetailService;

    @Test
    public void createNewAppUser() throws Exception {
        // Arrange common data
        Personne personne = new Personne(1, "DUPONT", "JEAN", "jean@gmail.com", "0656878987");
        Personne personne2 = new Personne(1, "DUPOND", "REMI", "remi@gmail.com", "0656878988");
        Lieu marseille = new Lieu(1, "MARSEILLE", "13100");
        Lieu caen = new Lieu(2, "Caen", "14000");
        Role praticien = new Role(1,"PRATICIEN");


        AppUserDto appuserDto = new AppUserDto();
        appuserDto.setNomAppUser("Dupont");
        appuserDto.setPrenomAppUser("Jean");
        appuserDto.setEmail("jean@gmail.com");
        appuserDto.setTel("0656878987");
        appuserDto.setNomVille("Marseille");
        appuserDto.setCodePostal("13100");
        appuserDto.setNumAdeli("111111111");
        appuserDto.setNomRole("PRATICIEN");
        appuserDto.setPassword(passwordEncoder.encode("test"));
        appuserDto.setActive(true);



        AppUserDto appuserCreatedDto = new AppUserDto();
        appuserCreatedDto.setIdAppUser(1);
        appuserCreatedDto.setNomAppUser("DUPONT");
        appuserCreatedDto.setPrenomAppUser("JEAN");
        appuserCreatedDto.setEmail("jean@gmail.com");
        appuserCreatedDto.setTel("0656878987");
        appuserCreatedDto.setNomVille("MARSEILLE");
        appuserCreatedDto.setCodePostal("13100");
        appuserCreatedDto.setNumAdeli("111111111");
        appuserCreatedDto.setNomRole("PRATICIEN");
        appuserCreatedDto.setActive(true);
        appuserCreatedDto.setPassword(passwordEncoder.encode("test"));
        appuserCreatedDto.setActive(true);


        // Common mocks
        when(lieuRepository.findByNomVilleAndCodePostal("MARSEILLE", "13100")).thenReturn(marseille);
        when(appUserRepository.findByIdentiteEmail("jean@gmail.com")).thenReturn(null);
        when(personneRepository.save(any(Personne.class))).thenReturn(personne);
        when(roleRepository.findByNomRole("PRATICIEN")).thenReturn(praticien);

        when(appUserService.create(any(AppUserDto.class))).thenReturn(appuserCreatedDto);

        mockMvc.perform(post("/api/praticien" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appuserDto)))
                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.nomVille", is("MARSEILLE")))
                .andExpect(jsonPath("$.codePostal", is("13100")))
                .andExpect(jsonPath("$.nomRole", is("PRATICIEN")))
                .andExpect(jsonPath("$.nomAppUser", is("DUPONT")))
                .andExpect(jsonPath("$.prenomAppUser", is("JEAN")))
                .andExpect(jsonPath("$.numAdeli", is("111111111")))
                .andExpect(jsonPath("$.email", is("jean@gmail.com")))
                .andExpect(jsonPath("$.tel", is("0656878987")))
                .andExpect(jsonPath("$.isActive", is(true)))
                .andExpect(jsonPath("$.password",is( passwordEncoder.encode("test"))))
                .andExpect(jsonPath("$.idAppUser", is(1)));


        // Test Case 3: Creer nouvel utilisateur qd ville n'est pas dans la base de données
        AppUserDto appuserDtoNewCity = new AppUserDto();
        appuserDtoNewCity.setNomAppUser("Dupont");
        appuserDtoNewCity.setPrenomAppUser("Jean");
        appuserDtoNewCity.setEmail("jean@gmail.com");
        appuserDtoNewCity.setTel("0656878988");
        appuserDtoNewCity.setNomVille("Caen");
        appuserDtoNewCity.setCodePostal("14000");
        appuserDtoNewCity.setNumAdeli("111111112");
        appuserDtoNewCity.setNomRole("PRATICIEN");
        appuserDtoNewCity.setPassword(passwordEncoder.encode("test"));
        appuserDtoNewCity.setActive(true);



        AppUserDto appuserNewCityCreatedDto = new AppUserDto();
        appuserNewCityCreatedDto.setIdAppUser(2);
        appuserNewCityCreatedDto.setNomAppUser("DUPOND");
        appuserNewCityCreatedDto.setPrenomAppUser("REMI");
        appuserNewCityCreatedDto.setEmail("remi@gmail.com");
        appuserNewCityCreatedDto.setTel("0656878988");
        appuserNewCityCreatedDto.setNomVille("CAEN");
        appuserNewCityCreatedDto.setCodePostal("14000");
        appuserNewCityCreatedDto.setNumAdeli("111111112");
        appuserNewCityCreatedDto.setNomRole("PRATICIEN");
        appuserNewCityCreatedDto.setActive(true);
        appuserNewCityCreatedDto.setPassword(passwordEncoder.encode("test"));
        appuserNewCityCreatedDto.setActive(true);


        // Common mocks
        when(lieuRepository.findByNomVilleAndCodePostal("CAEN", "14000")).thenReturn(null);
        when(lieuRepository.save(any(Lieu.class))).thenReturn(caen);
        when(appUserRepository.findByIdentiteEmail("remi@gmail.com")).thenReturn(null);
        when(personneRepository.save(any(Personne.class))).thenReturn(personne2);
        when(roleRepository.findByNomRole("PRATICIEN")).thenReturn(praticien);

        when(appUserService.create(any(AppUserDto.class))).thenReturn(appuserNewCityCreatedDto);

        mockMvc.perform(post("/api/praticien" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appuserDtoNewCity)))
                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.nomVille", is("CAEN")))
                .andExpect(jsonPath("$.codePostal", is("14000")))
                .andExpect(jsonPath("$.nomRole", is("PRATICIEN")))
                .andExpect(jsonPath("$.nomAppUser", is("DUPOND")))
                .andExpect(jsonPath("$.prenomAppUser", is("REMI")))
                .andExpect(jsonPath("$.numAdeli", is("111111112")))
                .andExpect(jsonPath("$.email", is("remi@gmail.com")))
                .andExpect(jsonPath("$.tel", is("0656878988")))
                .andExpect(jsonPath("$.isActive", is(true)))
                .andExpect(jsonPath("$.password",is( passwordEncoder.encode("test"))))
                .andExpect(jsonPath("$.idAppUser", is(2)));

    }







}
