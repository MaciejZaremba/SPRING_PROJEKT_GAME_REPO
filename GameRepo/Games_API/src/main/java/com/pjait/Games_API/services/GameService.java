package com.pjait.Games_API.services;

import com.pjait.Games_Data.entities.*;
import com.pjait.Games_Data.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService extends BaseService<Game>{
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        super(gameRepository);
        this.gameRepository = gameRepository;
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game findGameById(Long id) {
        return gameRepository.findById(id).get();
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public void addGame(Game game) {
        gameRepository.save(game);
    }

    public void updateGame(Game game, Long id) {
        gameRepository.save(game);
    }

    public Optional<Game> findGameByName(String name) {
        return gameRepository.findByName(name);
    }

    public Optional<Game> findGameByApiId(Long apiId)
    {
        return gameRepository.findByApiId(apiId);
    }

    public List<Game> findAllByRating(Double rating)
    {
        return gameRepository.findAllByRating(rating);
    }

    public List<Game> findAllByReleaseYear(Integer releaseYear)
    {
        return gameRepository.findAllByReleaseYear(releaseYear);
    }

    public List<Game> findAllByGenres(List<Genre> genres)
    {
        return gameRepository.findAllByGenres(genres);
    }

    public List<Game> findAllByThemes(List<Theme> themes)
    {
        return gameRepository.findAllByThemes(themes);
    }

    public List<Game> findAllByCompanies(List<Company> companies)
    {
        return gameRepository.findAllByCompanies(companies);
    }

    public List<Game> findAllByPlatforms(List<Platform> platforms)
    {
        return gameRepository.findAllByPlatforms(platforms);
    }
}
