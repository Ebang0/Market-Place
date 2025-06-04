package com.marketplace.Gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable()) // Désactivation CSRF pour API REST
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().permitAll() // Le filtrage se fait dans notre AuthenticationFilter
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // Désactive l'authentification Basic
            .formLogin(formLogin -> formLogin.disable()) // Désactive le form login
            .build();
    }
}