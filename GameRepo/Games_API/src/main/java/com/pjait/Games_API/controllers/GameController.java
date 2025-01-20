package com.pjait.Games_API.controllers;

import com.pjait.Games_API.services.*;
import com.pjait.Games_Data.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;
    private final GenreService genreService;
    private final ThemeService themeService;
    private final CompanyService companyService;
    private final PlatformService platformService;

    @Autowired
    public GameController(GameService gameService, GenreService genreService, ThemeService themeService, CompanyService companyService, PlatformService platformService) {
        this.gameService = gameService;
        this.genreService = genreService;
        this.themeService = themeService;
        this.companyService = companyService;
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAllGames();
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.findGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Game>> getGameByName(@PathVariable String name) {
        Optional<Game> game = gameService.findGameByName(name);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Game>> getGamesByRating(@PathVariable double rating) {
        List<Game> games = gameService.findAllByRating(rating);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);

    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Optional<Game>> getGamesByApiId(@PathVariable Long apiId) {
        Optional<Game> game = gameService.findGameByApiId(apiId);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/genres/{genre}")
    public ResponseEntity<List<Game>> getGamesByGenres(@PathVariable String genre) {
        Genre foundGenre = genreService.findGenreByName(genre);
        if (foundGenre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Genre> genres = new ArrayList<>();
        genres.add(foundGenre);
        List<Game> games = gameService.findAllByGenre(genres);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);

    }

    @GetMapping("/themes/{theme}")
    public ResponseEntity<List<Game>> getGamesByThemes(@PathVariable String theme) {
        Theme foundTheme = themeService.findThemeByName(theme);
        if (foundTheme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Theme> themes = new ArrayList<>();
        themes.add(foundTheme);
        List<Game> games = gameService.findAllByTheme(themes);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);

    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Game>> getGamesByYear(@PathVariable int year) {
        List<Game> game = gameService.findAllByReleaseYear(year);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/companies/{company}")
    public ResponseEntity<List<Game>> getGamesByCompany(@PathVariable String company) {
        Company foundCompany = companyService.findCompanyByName(company);
        if (foundCompany == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Company> companies = new ArrayList<>();
        companies.add(foundCompany);
        List<Game> game = gameService.findAllByCompany(companies);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/platforms/{platform}")
    public ResponseEntity<List<Game>> getGamesByPlatform(@PathVariable String platform) {
        Platform foundPlatform = platformService.findPlatformByName(platform);
        if (foundPlatform == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Platform> platforms = new ArrayList<>();
        platforms.add(foundPlatform);
        List<Game> game = gameService.findAllByPlatform(platforms);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        if(gameService.findGameByName(game.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        gameService.addGame(game);
        if(gameService.findGameByName(game.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameService.findGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game gameTest = gameService.findGameById(id);
        if (gameTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(game == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        gameService.updateGame(game, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
