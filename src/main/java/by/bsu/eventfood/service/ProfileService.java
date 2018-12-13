package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.resource.ProfileResource;
import by.bsu.eventfood.controller.resource.ProfileResource.ShortPlaceResource;
import by.bsu.eventfood.model.Client;

import java.util.List;

public interface ProfileService {
    ProfileResource getCurrentProfileInfo(Client client);

    ProfileResource getProfileInfo(Long id);

    List<ShortPlaceResource> getClientPlaces(Client client);
}
