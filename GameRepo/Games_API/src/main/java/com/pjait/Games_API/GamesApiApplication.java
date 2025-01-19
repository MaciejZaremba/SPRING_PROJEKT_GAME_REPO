package com.pjait.Games_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pjait.Games_Data", "com.pjait.Games_API"})
public class GamesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesApiApplication.class, args);
	}

}
