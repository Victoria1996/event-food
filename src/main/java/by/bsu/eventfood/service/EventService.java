package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.controller.resource.EventByIdResource;
import by.bsu.eventfood.controller.resource.EventWithPlaceResource;
import by.bsu.eventfood.controller.resource.PlaceResourceWithDescAndTime;
import by.bsu.eventfood.controller.resource.ShortEventResource;

import java.util.Date;
import java.util.List;

public interface EventService {
    void addEvent(AddEventDto addEventDto);

    void updateEvent(AddEventDto addEventDto, Long eventId);

    List<ShortEventResource> findPlaceEvents(Long placeId);

    List<PlaceResourceWithDescAndTime> getAllPlaces(boolean hasActiveEvents);

    List<EventWithPlaceResource> getAllEvents(boolean isEnded);

    EventByIdResource findEvent(Long id, Date parseDate);
}
