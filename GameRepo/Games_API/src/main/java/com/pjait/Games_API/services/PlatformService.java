package com.pjait.Games_API.services;

import com.pjait.Games_Data.entities.Platform;
import com.pjait.Games_Data.repositories.PlatformRepository;
import com.pjait.Games_Data.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatformService extends BaseService<Platform> {
    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformService(PlatformRepository platformRepository) {
        super(platformRepository);
        this.platformRepository = platformRepository;
    }

    public List<Platform> findAllPlatforms() {
        return findAll();
    }

    public Platform findPlatformById(Long id) {
        return findById(id);
    }

    public void deletePlatform(Long id) {
        deleteById(id);
    }

    public void addPlatform(Platform platform) {
        add(platform);
    }

    public void updatePlatform(Platform platform, Long id) {
        update(platform, id);
    }

    public Optional<Platform> findPlatformByName(String name) {
        return platformRepository.findByName(name);
    }

    public Optional<Platform> findPlatformByApiId(Long apiId) {
        return platformRepository.findByApiId(apiId);
    }
}
