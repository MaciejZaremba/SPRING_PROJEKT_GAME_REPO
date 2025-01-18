package com.pjait.Games_Updater.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjait.Games_Data.entities.*;
import com.pjait.Games_Data.repositories.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.client5.http.fluent.Request;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {
    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final ThemeRepository themeRepository;
    private final CompanyRepository companyRepository;
    private final PlatformRepository platformRepository;
    private final RestTemplate restTemplate;
    private final String clientID = "tm7bjkd9zl6nifcxx3e2a53ohhemhr";
    private final String accessKey = "Bearer i3xwqrk258uxd8y3m6fzs2ourga9om";

    @Autowired
    public DatabaseService(GameRepository gameRepository, GenreRepository genreRepository, ThemeRepository themeRepository, CompanyRepository companyRepository, PlatformRepository platformRepository, RestTemplate restTemplate) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.themeRepository = themeRepository;
        this.companyRepository = companyRepository;
        this.platformRepository = platformRepository;
        this.restTemplate = restTemplate;
    }

    @SneakyThrows
    public void fetchAndSaveGames(){
        int limit = 50;
        int offset = 0;
        String apiURL = "https://api.igdb.com/v4/games";
        List<Game> games = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        while(true){
            String requestBody = String.format(
                    "fields id, name, genres, themes, involved_companies, platforms, rating, first_release_date;\nlimit %d;\noffset %d",
                    limit, offset
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, null)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            if(jsonResponse.isEmpty()) break;

            for (JsonNode gameNode : jsonResponse) {
                Game game = new Game();
                game.setApiID( gameNode.get("id").asInt());
                game.setName( gameNode.get("name").asText());
                game.setRating(gameNode.get("rating").asDouble());
                game.setReleaseYear(gameNode.has("release_dates") && gameNode.get("release_dates").has(0)
                        ? Instant.ofEpochSecond(gameNode.get("release_dates").get(0).get("date").asLong())
                        .atZone(ZoneId.systemDefault())
                        .getYear()
                        : null);


            }
        }
    }

    @SneakyThrows
    private void fetchAndSaveGenres(){
        int limit = 50;
        String apiURL = "https://api.igdb.com/v4/genres";
        List<Genre> genres = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = String.format(
                "fields id, name;\nlimit %d;",
                limit
        );

        String response = Request.post(apiURL)
                .addHeader("Client-ID", clientID)
                .addHeader("Authorization", accessKey)
                .bodyString(requestBody, null)
                .execute()
                .returnContent()
                .asString();

        JsonNode jsonResponse = mapper.readTree(response);

        for (JsonNode genreNode : jsonResponse) {
            Genre genre = new Genre();
            genre.setApiID( genreNode.get("id").asInt());
            genre.setName( genreNode.get("name").asText());
            genres.add(genre);
        }
    }

    @SneakyThrows
    private void fetchAndSaveThemes(){
        int limit = 50;
        String apiURL = "https://api.igdb.com/v4/themes";
        List<Theme> themes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = String.format(
                "fields id, name;\nlimit %d;",
                limit
        );

        String response = Request.post(apiURL)
                .addHeader("Client-ID", clientID)
                .addHeader("Authorization", accessKey)
                .bodyString(requestBody, null)
                .execute()
                .returnContent()
                .asString();

        JsonNode jsonResponse = mapper.readTree(response);

        for (JsonNode themeNode : jsonResponse) {
            Theme theme = new Theme();
            theme.setApiID( themeNode.get("id").asInt());
            theme.setName( themeNode.get("name").asText());
            themes.add(theme);
        }
    }

    @SneakyThrows
    private void fetchAndSaveCompanies() {
        int limit = 50;
        int offset = 0;
        String apiURL = "https://api.igdb.com/v4/companies";
        List<Company> companies = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        while (true) {
            String requestBody = String.format(
                    "fields id, name, country;\nlimit %d;\noffset %d",
                    limit, offset
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, null)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            for (JsonNode companyNode : jsonResponse) {
                Company company = new Company();
                company.setApiID(companyNode.get("id").asInt());
                company.setName(companyNode.get("name").asText());

                if (companyNode.has("country") && !companyNode.get("country").isNull() && !companyNode.get("country").asText().isEmpty()) {
                    company.setCountry(companyNode.get("country").asText());
                } else {
                    company.setCountry(null);
                }
                companies.add(company);
            }
        }
    }

    @SneakyThrows
    private void fetchAndSavePlatforms() {
        int limit = 50;
        int offset = 0;
        String apiURL = "https://api.igdb.com/v4/platforms";
        List<Platform> platforms = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        while (true) {
            String requestBody = String.format(
                    "fields id, name;\nlimit %d;\noffset %d",
                    limit, offset
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, null)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            for (JsonNode companyNode : jsonResponse) {
                Platform platform = new Platform();
                platform.setApiID(companyNode.get("id").asInt());
                platform.setName(companyNode.get("name").asText());
                platforms.add(platform);
            }
        }
    }
}
