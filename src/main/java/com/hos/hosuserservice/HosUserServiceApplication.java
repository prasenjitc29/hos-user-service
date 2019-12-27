package com.hos.hosuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HosUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HosUserServiceApplication.class, args);
	}

}
