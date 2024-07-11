package org.gestion_patient.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Autoriser les requêtes depuis n'importe quelle origine
        config.addAllowedHeader("*"); // Autoriser les en-têtes dans les requêtes
        config.addAllowedMethod("*"); // Autoriser les méthodes HTTP (GET, POST, PUT, DELETE, etc.)
        config.addExposedHeader("ETag"); // Exposer l'en-tête ETag
        config.addExposedHeader("error-message"); // Exposer l'en-tête error-message
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}