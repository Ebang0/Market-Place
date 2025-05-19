package com.marketplace.Auth_Gestion_Utilisateur.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecureConfig {
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
    {
        //.hasRole("Role")
        //.hasAnyRole("Role1","Role2",......,"Rolen")
        http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/api/role/**").permitAll()
            .requestMatchers("/api/auth_util/inscription","/api/auth_util/connexion").permitAll()
            .anyRequest().authenticated());
        return http.build();
    }

    /* @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails user = User.builder()
                            .username(null)
                            .password(null)
                            .roles(null)
                            .build();

        return new InMemoryUserDetailsManager(user);
    } */

    //Crypte les données 
    @Bean 
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

     @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,PasswordEncoder passwordEncoder) throws Exception
    {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(null).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
    
}
