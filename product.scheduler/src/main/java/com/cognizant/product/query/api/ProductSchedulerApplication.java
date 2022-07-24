package com.cognizant.product.query.api;

import com.cognizant.core.configuration.AppMessageQueueConfig;
import com.cognizant.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Import({ AxonConfig.class,  AppMessageQueueConfig.class })
public class ProductSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSchedulerApplication.class, args);
	}

}
