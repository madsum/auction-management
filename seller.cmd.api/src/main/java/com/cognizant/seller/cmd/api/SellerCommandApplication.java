package com.cognizant.seller.cmd.api;

import com.cognizant.user.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class SellerCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerCommandApplication.class, args);
	}

}
