package com.pjait.Games_Updater;

import com.pjait.Games_Data.repositories.GameRepository;
import com.pjait.Games_Updater.services.DatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.pjait.Games_Data.repositories")
@EntityScan("com.pjait.Games_Data.entities")
public class GamesUpdaterApplication implements CommandLineRunner {

	@Autowired
	private final DatabaseService databaseService;

	@Autowired
	public GamesUpdaterApplication(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GamesUpdaterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		databaseService.fetchAndSaveGames();
//		databaseService.fetchAndSaveGenres();
//		databaseService.fetchAndSaveThemes();
//		databaseService.fetchAndSaveCompanies();
//		databaseService.fetchAndSavePlatforms();
		databaseService.updateGameRelationships();
	}
}
