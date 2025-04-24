package com.ada.tecnichal_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ada.tecnichal_test")
@EntityScan("com.ada.tecnichal_test.")
@EnableJpaRepositories("com.ada.tecnichal_test.infraestructure.repository")
public class TecnichalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecnichalTestApplication.class, args);
	}

}
