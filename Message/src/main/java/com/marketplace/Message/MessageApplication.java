package com.marketplace.Message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@FeignClient
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

}
