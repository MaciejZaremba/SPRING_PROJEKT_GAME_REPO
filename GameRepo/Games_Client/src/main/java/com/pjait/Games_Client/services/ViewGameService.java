package com.pjait.Games_Client.services;

import com.pjait.Games_Data.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequestMapping
public class ViewGameService extends ViewBaseService {
    public ViewGameService(RestClient restClient) {
        super(restClient);
    }

    public List<Game> getAllGames() {
        return viewAllEntities("/games");
    }

    public Game getGameById(Long id) {
        return (Game) viewEntityByField("/games/id/" + id, new ParameterizedTypeReference<>() {});
    }

    public Game getGameByName(String name) {
        return (Game) viewEntityByField("/games/name/" + name, new ParameterizedTypeReference<>() {});
    }

    public Game getGameByApiId(Long id) {
        return (Game) viewEntityByField("/games/apiId/" + id, new ParameterizedTypeReference<>() {});
    }

    public void deleteGame(Long id) {
        deleteEntity("/games/id/" + id, id);
    }

    public void updateGame(Long id, Game game) {
        updateEntity("/games/id/" + id, game);
    }

    public void addGame(Game game) {
        createEntity("/games", game);
    }

    public List<Genre> getAllGenresByGameId(Long id) {
        return viewAllEntitiesOfRelationById("/games/genres/" + id, new ParameterizedTypeReference<>() {});
    }

    public List<Platform> getAllPlatformsByGameId(Long id) {
        return viewAllEntitiesOfRelationById("/games/platform/" + id, new ParameterizedTypeReference<>() {});
    }

    public List<Theme> getAllThemesByGameId(Long id) {
        return viewAllEntitiesOfRelationById("/games/themes/" + id, new ParameterizedTypeReference<>() {});
    }

    public List<Company> getAllCompaniesByGameId(Long id) {
        return viewAllEntitiesOfRelationById("/games/company/" + id, new ParameterizedTypeReference<>() {});
    }
}
