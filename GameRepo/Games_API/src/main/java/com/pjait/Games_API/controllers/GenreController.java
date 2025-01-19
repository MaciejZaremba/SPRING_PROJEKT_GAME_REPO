package com.pjait.Games_API.controllers;

import com.pjait.Games_API.services.GenreService;
import com.pjait.Games_Data.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.findAllGenres();
        if (genres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Genre genre = genreService.findGenreById(id);
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Optional<Genre>> getGenreByName(@PathVariable String name) {
        Optional<Genre> genre = genreService.findGenreByName(name);
        if (genre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Optional<Genre>> getGenreByApiId(@PathVariable Long apiId) {
        Optional<Genre> genre = genreService.findGenreByApiId(apiId);
        if (genre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createGenre(@RequestBody Genre genre) {
        if (genreService.findGenreByName(genre.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        genreService.addGenre(genre);
        if (genreService.findGenreByName(genre.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        Genre genre = genreService.findGenreById(id);
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        Genre genreTest = genreService.findGenreById(id);
        if (genreTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.updateGenre(genre, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
