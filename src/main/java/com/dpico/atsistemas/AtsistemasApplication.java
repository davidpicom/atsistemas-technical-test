package com.dpico.atsistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.*;

@EntityScan(basePackages = "com.example.atsistemas.prices.domain")
@SpringBootApplication(scanBasePackages = {"com.dpico.atsistemas.prices.infrastructure", "com.dpico.atsistemas.prices.application"})
public class AtsistemasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtsistemasApplication.class, args);
	}

}

