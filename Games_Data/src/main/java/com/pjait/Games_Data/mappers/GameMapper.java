package com.pjait.Games_Data.mappers;

import com.pjait.Games_Data.dtos.*;
import com.pjait.Games_Data.entities.Game;

import java.util.stream.Collectors;

public class GameMapper {
    public static GameDTO toDTO(Game game) {
        return new GameDTO(
                game.getId(),
                game.getApiID(),
                game.getName(),
                game.getReleaseYear(),
                game.getRating(),
                game.getGenres().stream()
                        .map(genre -> new GenreDTO(genre.getId(), genre.getName()))
                        .collect(Collectors.toList()),
                game.getPlatforms().stream()
                        .map(platform -> new PlatformDTO(platform.getId(), platform.getName()))
                        .collect(Collectors.toList()),
                game.getThemes().stream()
                        .map(theme -> new ThemeDTO(theme.getId(), theme.getName()))
                        .collect(Collectors.toList()),
                new CompanyDTO(
                        game.getCompany().getId(),
                        game.getCompany().getName(),
                        game.getCompany().getCountry()
                )
        );
    }
}
