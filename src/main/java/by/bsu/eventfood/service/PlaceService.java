package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.model.Client;

public interface PlaceService {
    void addPlace(AddPlaceDto addPlaceDto, Client client);

    void updatePlace(AddPlaceDto addPlaceDto, Long placeId);
}
