package com.marketplace.Auth_Gestion_Utilisateur.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.marketplace.Auth_Gestion_Utilisateur.service.implement.CustomServic;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecureConfig {
    private final CustomServic utilisateurService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth_util/inscription",
                    "/api/auth_util/connexion",
                    "/api/auth_util/profil/{id}",
                    "/api/role/all",
                    "/api/role/{id}",
                    "/api/auth_util/token/**"
                ).permitAll()
                .requestMatchers(
                    "/api/role/delete/",
                    "/api/role/save",
                    "/api/role/update/**",

                    "/api/auth_util/role/**",
                    "/api/auth_util/active/**",
                    "/api/auth_util/delete/**"
                ).hasRole("ADMIN")
                .anyRequest().authenticated()
            ).addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
    @Bean
    public JwtFilter jwtFilter()
    {
        return new JwtFilter(utilisateurService,jwtService());
    }

    @Bean
    public JwtService jwtService()
    {
        return new JwtService();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authBuilder.build();
    }
}
