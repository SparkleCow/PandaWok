package com.sparklecow.pandawok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PandawokApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandawokApplication.class, args);
	}

}
