package org.gestion_patient.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.authenticated;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    private AppUserDetailService appUserDetailService;
    private AuthenticationConfiguration authConfiguration;



    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configuration de la sécurité HTTP
        http.csrf(csrf -> csrf.disable()) // Désactive la protection CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Utilise des sessions sans état
                .headers(frameOptions -> frameOptions.disable()) // Désactive la protection des frames
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/login").permitAll()) // Permet les requêtes POST vers /login sans authentification
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()) // Toutes les autres requêtes nécessitent une authentification
                .addFilter(new JwtAuthenticationFilter(authenticationManager(authConfiguration))) // Ajoute un filtre d'authentification JWT
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); // Ajoute un filtre d'autorisation JWT avant le filtre UsernamePassword

        return http.build(); // Construit et retourne l'objet SecurityFilterChain
    }



    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();}



}
