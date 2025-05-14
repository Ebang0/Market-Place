package com.marketplace.Offre_Demande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OffreProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffreProduitApplication.class, args);
	}

}
