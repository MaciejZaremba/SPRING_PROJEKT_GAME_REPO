package com.pjait.Games_Client.services;

import com.pjait.Games_Data.exceptions.EntityNotFound;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public abstract class ViewBaseService<T> {
    private final RestClient restClient;

    public ViewBaseService(RestClient restClient) {
        this.restClient = restClient;
    }

    protected <T> List<T> viewAllEntities(String url) {
        List<T> entities = null;
        try {
            entities = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            if (entities != null && entities.isEmpty()) {
                throw new EntityNotFound();
            }
        } catch (Exception e) {
            throw new EntityNotFound();
        }
        return entities;
    }

    protected <T> List<T> viewAllEntitiesOfRelationById(String url, ParameterizedTypeReference<List<T>> typeReference) {
        List<T> entities = null;
        try {
            entities = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(typeReference);
            if (entities != null && entities.isEmpty()) {
                throw new EntityNotFound();
            }
        } catch (Exception e) {
            throw e;
        }
        return entities;
    }

    protected <T> T viewEntityByField(String url,ParameterizedTypeReference<T> typeReference) {
        try{
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(typeReference);
        } catch (Exception e) {
            throw new EntityNotFound();
        }
    }

    protected <T> List<T> viewEntitiesByField(String url, Object field) {
        List<T> entities = null;
        String modifiedUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("field", field)
                .toUriString();
        try{
            entities = restClient.get()
                .uri(modifiedUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (entities != null && entities.isEmpty()) {
            throw new EntityNotFound();
        }
        } catch (Exception e) {
            throw new EntityNotFound();
        }
        return entities;
    }

    protected void deleteEntity(String url, Long id) {
        restClient.delete()
                .uri(url)
                .retrieve()
                .toBodilessEntity();
    }

    protected <T> void updateEntity(String url, T entity) {
        restClient.patch()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(entity)
                .retrieve()
                .toBodilessEntity();
    }

    protected <T> void createEntity(String url, T entity) {
        restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(entity)
                .retrieve()
                .toBodilessEntity();
    }


}
