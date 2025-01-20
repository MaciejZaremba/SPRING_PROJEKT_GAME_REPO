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

import java.util.ArrayList;
import java.util.List;

import static org.apache.hc.core5.http.ContentType.TEXT_PLAIN;

@Service
public class DatabaseService {
    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final ThemeRepository themeRepository;
    private final CompanyRepository companyRepository;
    private final PlatformRepository platformRepository;
    private final String clientID = "tm7bjkd9zl6nifcxx3e2a53ohhemhr";
    private final String accessKey = "Bearer i3xwqrk258uxd8y3m6fzs2ourga9om";
    private final int capacity = 500;

    @Autowired
    public DatabaseService(GameRepository gameRepository, GenreRepository genreRepository, ThemeRepository themeRepository, CompanyRepository companyRepository, PlatformRepository platformRepository, RestTemplate restTemplate) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.themeRepository = themeRepository;
        this.companyRepository = companyRepository;
        this.platformRepository = platformRepository;
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
                    "fields id, name, rating, first_release_date;\nlimit %d;\noffset %d;",
                    limit, offset
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, TEXT_PLAIN)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            if(jsonResponse.isEmpty()) break;

            for (JsonNode gameNode : jsonResponse) {
                Game game = new Game();
                game.setApiId(gameNode.get("id").asLong());
                game.setName(gameNode.get("name").asText());

                if(gameNode.has("rating")) {
                    game.setRating(gameNode.get("rating").asDouble());
                } else {
                    game.setRating(null);
                }
                if(gameNode.has("first_release_date")) {
                    Long epoch = gameNode.get("first_release_date").asLong();
                    String date = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date(epoch * 1000));

                    game.setReleaseYear(Integer.valueOf(date));
                } else {
                    game.setReleaseYear(null);
                }
                games.add(game);
            }

            gameRepository.saveAll(games);
            games.clear();
            offset += limit;
            if(offset>capacity) break;
        }
    }

    @SneakyThrows
    public void fetchAndSaveGenres(){
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
                .bodyString(requestBody, TEXT_PLAIN)
                .execute()
                .returnContent()
                .asString();

        JsonNode jsonResponse = mapper.readTree(response);

        for (JsonNode genreNode : jsonResponse) {
            Genre genre = new Genre();
            genre.setApiId(genreNode.get("id").asLong());
            genre.setName(genreNode.get("name").asText());
            genres.add(genre);
        }
        genreRepository.saveAll(genres);
    }

    @SneakyThrows
    public void fetchAndSaveThemes(){
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
                .bodyString(requestBody, TEXT_PLAIN)
                .execute()
                .returnContent()
                .asString();

        JsonNode jsonResponse = mapper.readTree(response);

        for (JsonNode themeNode : jsonResponse) {
            Theme theme = new Theme();
            theme.setApiId(themeNode.get("id").asLong());
            theme.setName(themeNode.get("name").asText());
            themes.add(theme);
        }
        themeRepository.saveAll(themes);

    }

    @SneakyThrows
    public void fetchAndSaveCompanies() {
        int limit = 50;
        int offset = 0;
        String apiURL = "https://api.igdb.com/v4/companies";
        List<Company> companies = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        while (true) {
            String requestBody = String.format(
                    "fields id, name, country;\nlimit %d;\noffset %d;",
                    limit, offset
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, TEXT_PLAIN)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            if(jsonResponse.isEmpty()) break;

            for (JsonNode companyNode : jsonResponse) {
                Company company = new Company();
                company.setApiId(companyNode.get("id").asLong());
                company.setName(companyNode.get("name").asText());
                companies.add(company);
            }
            companyRepository.saveAll(companies);
            companies.clear();
            offset += limit;
            if(offset>capacity) break;
        }
    }

    @SneakyThrows
    public void fetchAndSavePlatforms() {
        int limit = 50;
        int offset = 0;
        String apiURL = "https://api.igdb.com/v4/platforms";
        List<Platform> platforms = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        while (true) {
            String requestBody = String.format(
                    "fields id, name;\nlimit %d;\noffset %d;",
                    limit, offset
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, TEXT_PLAIN)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            if(jsonResponse.isEmpty()) break;

            for (JsonNode platformNode : jsonResponse) {
                Platform platform = new Platform();
                platform.setApiId(platformNode.get("id").asLong());
                platform.setName(platformNode.get("name").asText());
                platforms.add(platform);
            }
            platformRepository.saveAll(platforms);
            platforms.clear();
            offset += limit;
            if(offset>capacity) break;
        }
    }

    @SneakyThrows
    public void updateGameRelationships() {
        String apiURL = "https://api.igdb.com/v4/games";
        List<Game> games = gameRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();

        for (Game game : games) {
            String requestBody = String.format(
                    "fields genres, themes, platforms, involved_companies;\nwhere id = %d;",
                    game.getApiId()
            );

            String response = Request.post(apiURL)
                    .addHeader("Client-ID", clientID)
                    .addHeader("Authorization", accessKey)
                    .bodyString(requestBody, TEXT_PLAIN)
                    .execute()
                    .returnContent()
                    .asString();

            JsonNode jsonResponse = mapper.readTree(response);

            if (jsonResponse.isEmpty()) continue;

            JsonNode gameNode = jsonResponse.get(0);

            if (gameNode.has("genres")) {
                List<Genre> genres = new ArrayList<>();
                for (JsonNode genreApiId : gameNode.get("genres")) {
                    Genre genre = genreRepository.findByApiId(genreApiId.asLong());
                    if (genre != null) {
                        genres.add(genre);
                    }
                }
                game.setGenres(genres);
            }

            if (gameNode.has("themes")) {
                List<Theme> themes = new ArrayList<>();
                for (JsonNode themeApiId : gameNode.get("themes")) {
                    Theme theme = themeRepository.findByApiId(themeApiId.asLong());
                    if (theme != null) {
                        themes.add(theme);
                    }
                }
                game.setThemes(themes);
            }

            if (gameNode.has("platforms")) {
                List<Platform> platforms = new ArrayList<>();
                for (JsonNode platformApiId : gameNode.get("platforms")) {
                    Platform platform = platformRepository.findByApiId(platformApiId.asLong());
                    if (platform != null) {
                        platforms.add(platform);
                    }
                }
                game.setPlatforms(platforms);
            }

            if (gameNode.has("involved_companies")) {
                List<Company> companies = new ArrayList<>();
                for (JsonNode companyApiId : gameNode.get("involved_companies")) {
                    Company company = companyRepository.findByApiId(companyApiId.asLong());
                    if (company != null) {
                        companies.add(company);
                    }
                }
                game.setCompanies(companies);
            }

            gameRepository.save(game);
        }
    }
}
