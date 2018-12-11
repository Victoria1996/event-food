package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddEventDto;

public interface EventService {
    void addEvent(AddEventDto addEventDto);

    void updateEvent(AddEventDto addEventDto, Long eventId);
}
