package com.pjait.Games_Data.repositories;


import com.pjait.Games_Data.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
    Optional<Genre> findById(Long id);
}
