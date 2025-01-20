package com.pjait.Games_Data.repositories;


import com.pjait.Games_Data.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
    Genre findByApiId(Long apiId);
}
