package com.example.cotonavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CotonavirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotonavirusTrackerApplication.class, args);
	}

}
