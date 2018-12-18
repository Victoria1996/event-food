package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.PlaceReservationDto;
import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.model.Client;

import java.util.Date;

public interface ReservationService {
    void reserve(ReservationDto dto, Client client);

    void enrichWithAvailableTables(PlaceReservationDto dto, Date date);
}
