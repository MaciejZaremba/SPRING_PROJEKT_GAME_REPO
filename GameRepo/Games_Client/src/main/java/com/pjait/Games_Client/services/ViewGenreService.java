package com.pjait.Games_Client.services;

import com.pjait.Games_Data.entities.Genre;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ViewGenreService extends ViewBaseService{
    public ViewGenreService(RestClient restClient) {
        super(restClient);
    }

    public List<Genre> getAllGenres() {
        return viewAllEntities("/genres");
    }

    public Genre getGenreById(Long id) {
        return (Genre) viewEntityByField("/genres/id/" + id, new ParameterizedTypeReference<>() {});
    }

    public Genre getGenreByName(String name) {
        return (Genre) viewEntityByField("/genres/name/" + name, new ParameterizedTypeReference<>() {});
    }

    public Genre getGenreByApiId(Long id) {
        return (Genre) viewEntityByField("/genres/apiId/" + id, new ParameterizedTypeReference<>() {});
    }

    public void addGenre(Genre genre) {
        createEntity("/genres", genre);
    }

    public void updateGenre(Long id, Genre genre) {
        updateEntity("/genres/id/" + id, genre);
    }

    public void deleteGenre(Long id) {
        deleteEntity("/genres/id" + id, id);
    }

}
