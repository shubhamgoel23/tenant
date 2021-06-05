package com.bms.tenants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.github.cloudyrock.spring.v5.EnableMongock;

@SpringBootApplication
@EnableMongock
@EnableFeignClients
public class TenantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantsApplication.class, args);
	}

}
