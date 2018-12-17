package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.resource.PlaceResourceById;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.security.CustomUserDetails;

public interface PlaceService {
    void addPlace(AddPlaceDto addPlaceDto, Client client);

    void updatePlace(AddPlaceDto addPlaceDto, Long placeId);

    PlaceResourceById getPlaceById(Long id, CustomUserDetails customUserDetails);
}
