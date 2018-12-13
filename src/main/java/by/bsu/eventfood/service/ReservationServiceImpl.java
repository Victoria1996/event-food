package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.ReservationEvent;
import by.bsu.eventfood.model.ReservationPlace;
import by.bsu.eventfood.model.ReservationType;
import by.bsu.eventfood.repository.ReservationEventRepository;
import by.bsu.eventfood.repository.ReservationPlaceRepository;
import by.bsu.eventfood.service.mapper.ReservationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationEventRepository eventRepository;

    @Autowired
    private ReservationPlaceRepository placeRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void reserve(ReservationDto dto, Client client) {
        ReservationType reservationType = dto.getType();

        switch (reservationType) {
            case EVENT:
                ReservationEvent reservationEvent = reservationMapper.mapEvent(dto);
                reservationEvent.setClient(client);

                eventRepository.save(reservationEvent);

                log.info("Event reservation was saved");
                break;
            case PLACE:
                ReservationPlace reservationPlace = reservationMapper.mapPlace(dto);
                reservationPlace.setClient(client);

                placeRepository.save(reservationPlace);

                log.info("Place reservation was saved");
                break;
            default:
                break;
        }
    }
}
