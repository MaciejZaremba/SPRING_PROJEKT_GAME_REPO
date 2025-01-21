package com.pjait.Games_API.controllers;

import com.pjait.Games_API.services.ThemeService;
import com.pjait.Games_Data.entities.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<List<Theme>> getAllThemes() {
        List<Theme> themes = themeService.findAllThemes();
        if (themes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(themes, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Theme> getTheme(@PathVariable Long id) {
        Theme theme = themeService.findThemeById(id);
        if (theme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Theme> getThemeByName(@PathVariable String name) {
        Theme theme = themeService.findThemeByName(name);
        if (theme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }

    @GetMapping("/{apiId}")
    public ResponseEntity<Theme> getThemeByApiId(@PathVariable Long apiId) {
        Theme theme = themeService.findThemeByApiId(apiId);
        if (theme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createTheme(@RequestBody Theme theme) {
        if (themeService.findThemeByName(theme.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        themeService.addTheme(theme);
        if (themeService.findThemeByName(theme.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long id) {
        Theme theme = themeService.findThemeById(id);
        if (theme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        themeService.deleteTheme(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable Long id, @RequestBody Theme theme) {
        Theme themeTest = themeService.findThemeById(id);
        if (themeTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (theme == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        themeService.updateTheme(theme, id);
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }
}
