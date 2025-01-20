package com.pjait.Games_Data.repositories;

import com.pjait.Games_Data.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByName(String name);
    Theme findByApiId(Long apiId);
}
