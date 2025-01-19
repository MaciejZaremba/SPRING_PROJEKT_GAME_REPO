package com.pjait.Games_API.controllers;

import com.pjait.Games_API.services.GameService;
import com.pjait.Games_Data.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAllGames();
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);

    }

    @GetMapping("/{id}")
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

    @GetMapping("/genres/{genres}")
    public ResponseEntity<List<Game>> getGamesByGenres(@PathVariable List<Genre> genres) {
        List<Game> games = gameService.findAllByGenres(genres);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);

    }

    @GetMapping("/themes/{themes}")
    public ResponseEntity<List<Game>> getGamesByThemes(@PathVariable List<Theme> themes) {
        List<Game> games = gameService.findAllByThemes(themes);
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

    @GetMapping("/companies/{companies}")
    public ResponseEntity<List<Game>> getGamesByCompany(@PathVariable List<Company> companies) {
        List<Game> game = gameService.findAllByCompanies(companies);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/platforms/{platforms}")
    public ResponseEntity<List<Game>> getGamesByPlatform(@PathVariable List<Platform> platforms) {
        List<Game> game = gameService.findAllByPlatforms(platforms);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameService.findGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
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
