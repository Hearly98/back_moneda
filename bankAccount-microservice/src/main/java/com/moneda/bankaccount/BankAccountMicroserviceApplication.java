package com.moneda.bankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BankAccountMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountMicroserviceApplication.class, args);
	}

}
