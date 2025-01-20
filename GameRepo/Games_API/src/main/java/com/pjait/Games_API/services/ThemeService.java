package com.pjait.Games_API.services;

import com.pjait.Games_Data.entities.Theme;
import com.pjait.Games_Data.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService extends BaseService<Theme> {
    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeService(ThemeRepository themeRepository) {
        super(themeRepository);
        this.themeRepository = themeRepository;
    }

    public List<Theme> findAllThemes() {
        return findAllEntities();
    }

    public Theme findThemeById(Long id) {
        return findById(id);
    }

    public void deleteTheme(Long id) {
        deleteById(id);
    }

    public void addTheme(Theme theme) {
        add(theme);
    }

    public void updateTheme(Theme theme, Long id) {
        update(theme, id);
    }

    public Theme findThemeByName(String name) {
        return themeRepository.findByName(name);
    }

    public Theme findThemeByApiId(Long apiId) {
        return themeRepository.findByApiId(apiId);
    }
}