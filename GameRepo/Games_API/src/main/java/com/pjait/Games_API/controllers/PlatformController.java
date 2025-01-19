package com.pjait.Games_API.controllers;

import com.pjait.Games_API.services.PlatformService;
import com.pjait.Games_Data.entities.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/platforms")
public class PlatformController {
    private final PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<List<Platform>> getAllPlatforms() {
        List<Platform> platforms = platformService.findAllPlatforms();
        if (platforms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(platforms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platform> getPlatformById(@PathVariable Long id) {
        Platform platform = platformService.findPlatformById(id);
        if (platform == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(platform, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Platform>> getPlatformByName(@PathVariable String name) {
        Optional<Platform> platform = platformService.findPlatformByName(name);
        if (platform.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(platform, HttpStatus.OK);
    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Optional<Platform>> getPlatformByApiId(@PathVariable Long apiId) {
        Optional<Platform> platform = platformService.findPlatformByApiId(apiId);
        if (platform.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(platform, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPlatform(@RequestBody Platform platform) {
        if(platformService.findPlatformByName(platform.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        platformService.addPlatform(platform);
        if (platformService.findPlatformByName(platform.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable Long id) {
        Platform platform = platformService.findPlatformById(id);
        if (platform == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        platformService.deletePlatform(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePlatform(@PathVariable Long id, @RequestBody Platform platform) {
        Platform platformTest = platformService.findPlatformById(id);
        if (platformTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(platform == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        platformService.updatePlatform(platform, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
