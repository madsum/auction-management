package com.cognizant.product.query.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProductSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSchedulerApplication.class, args);
	}

}
