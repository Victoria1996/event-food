package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.model.Client;

public interface ReservationService {
    void reserve(ReservationDto dto, Client client);
}
