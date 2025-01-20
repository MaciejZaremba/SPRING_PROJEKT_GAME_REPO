package com.pjait.Games_Client.services;

import com.pjait.Games_Data.entities.Theme;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ViewThemeService extends ViewBaseService{
    public ViewThemeService(RestClient restClient) {
        super(restClient);
    }

    public List<Theme> getAllThemes() {
        return viewAllEntities("/themes");
    }

    public Theme getThemeById(Long id) {
        return (Theme) viewEntityByField("/themes/apiId/" + id, new ParameterizedTypeReference<>() {});
    }

    public Theme getThemeByName(String name) {
        return (Theme) viewEntityByField("/themes/name/" + name, new ParameterizedTypeReference<>() {});
    }

    public Theme getThemeByApiId(Long id) {
        return (Theme) viewEntityByField("/themes/apiId/" + id, new ParameterizedTypeReference<>() {});
    }

    public void addTheme(Theme theme) {
        createEntity("/themes", theme);
    }

    public void updateTheme(Long id, Theme theme) {
        updateEntity("/themes/id/" + id, theme);
    }

    public void deleteTheme(Long id) {
        deleteEntity("/themes/id" + id, id);
    }

}
