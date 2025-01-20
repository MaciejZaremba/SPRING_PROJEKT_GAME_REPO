package com.pjait.Games_Client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pjait.Games_Data", "com.pjait.Games_API", "com.pjait.Games_Client"})
public class GamesClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesClientApplication.class, args);
	}

}
