package com.marketplace.Notation_Commentaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NotationCommentaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotationCommentaireApplication.class, args);
	}

}
