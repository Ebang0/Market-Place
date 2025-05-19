package com.marketplace.Auth_Gestion_Utilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthGestionUtilisateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthGestionUtilisateurApplication.class, args);
	}

}
