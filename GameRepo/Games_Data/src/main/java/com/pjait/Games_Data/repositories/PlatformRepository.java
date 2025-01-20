package com.pjait.Games_Data.repositories;


import com.pjait.Games_Data.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Platform findByName(String name);
    Platform findByApiId(Long apiId);
}
