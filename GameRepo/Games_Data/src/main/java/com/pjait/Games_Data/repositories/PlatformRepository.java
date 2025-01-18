package com.pjait.Games_Data.repositories;


import com.pjait.Games_Data.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findByName(String name);
    Optional<Platform> findById(Long id);
}
