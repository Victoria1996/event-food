package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.resource.PlaceResourceWithDescAndTime;
import by.bsu.eventfood.model.Client;

import java.util.List;

public interface PlaceService {
    void addPlace(AddPlaceDto addPlaceDto, Client client);

    void updatePlace(AddPlaceDto addPlaceDto, Long placeId);

    List<PlaceResourceWithDescAndTime> getAllPlaces();
}
