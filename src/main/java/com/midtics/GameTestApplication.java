package com.midtics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GameTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameTestApplication.class, args);  
	}
}
