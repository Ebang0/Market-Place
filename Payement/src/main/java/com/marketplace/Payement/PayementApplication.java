package com.marketplace.Payement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PayementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayementApplication.class, args);
	}

}
