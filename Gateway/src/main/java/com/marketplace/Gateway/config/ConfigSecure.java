package com.marketplace.Gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigSecure {

    @Bean
    public RestTemplate template()
    {
        return new RestTemplate();
    }

    /* @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(exchanges -> exchanges
                // Routes publiques
                .pathMatchers(
                    "/api/auth_util/**",
                    "/api/offer/list",
                    "/api/**",
                    "/api/comments/getComment/{idOffre}"
                ).permitAll()
                
                // Routes protégées
                .pathMatchers("/api/request/**").authenticated()
                .pathMatchers("/api/offre/**").authenticated()
                .pathMatchers("/api/comments/{idUser}/postComment/{idOffre}").hasAuthority("ADMIN")
                
                // Toutes les autres routes
                .anyExchange().denyAll()
            ).build();
            
    } */
}