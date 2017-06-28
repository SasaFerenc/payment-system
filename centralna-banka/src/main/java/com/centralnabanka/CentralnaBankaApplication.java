package com.centralnabanka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CentralnaBankaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralnaBankaApplication.class, args);
	}
}
