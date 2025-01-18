package com.pjait.Games_Data.repositories;

import com.pjait.Games_Data.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
    Optional<Game> findById(Long id);
    List<Game> findAll();
    List<Game> findAllByRating(double rating);
    List<Game> findAllByReleaseYear(int releaseYear);
    List<Game> findAllByGenre(String genre);
    List<Game> findAllByTheme(String theme);
    List<Game> findAllByCompany(String company);
    List<Game> findAllByPlatform(String platform);


}
