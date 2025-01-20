package com.pjait.Games_Data.repositories;

import com.pjait.Games_Data.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
    Optional<Game> findByApiId(Long apiId);
    List<Game> findAllByRating(Double rating);
    List<Game> findAllByReleaseYear(Integer releaseYear);
    List<Game> findAllByGenres(List<Genre> genres);
    List<Game> findAllByThemes(List<Theme> themes);
    List<Game> findAllByCompanies(List<Company> companies);
    List<Game> findAllByPlatforms(List<Platform> platforms);

}
