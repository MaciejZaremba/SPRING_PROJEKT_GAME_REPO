package com.pjait.Games_Client.services;

import com.pjait.Games_Data.entities.Platform;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ViewPlatformService extends ViewBaseService{
    public ViewPlatformService(RestClient restClient) {
        super(restClient);
    }

    public List<Platform> getAllPlatforms() {
        return viewAllEntities("/platforms");
    }

    public Platform getPlatformById(Long id) {
        return (Platform) viewEntityByField("/platforms/id/" + id, new ParameterizedTypeReference<Platform>() {});
    }

    public Platform getPlatformByName(String name) {
        return (Platform) viewEntityByField("/platforms/name/" + name, new ParameterizedTypeReference<Platform>() {});
    }

    public Platform getPlatformByApiId(Long id) {
        return (Platform) viewEntityByField("/platforms/apiId/" + id, new ParameterizedTypeReference<Platform>() {});
    }

    public void addPlatform(Platform platform) {
        createEntity("/platforms", platform);
    }

    public void updatePlatform(Long id, Platform platform) {
        updateEntity("/platforms/id/" + id, platform);
    }

    public void deletePlatform(Long id) {
        deleteEntity("/platforms/id" + id, id);
    }

}
