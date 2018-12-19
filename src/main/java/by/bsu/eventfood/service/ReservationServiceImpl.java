package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.PlaceReservationDto;
import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.controller.resource.EventByIdResource;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.ReservationEvent;
import by.bsu.eventfood.model.ReservationPlace;
import by.bsu.eventfood.model.ReservationType;
import by.bsu.eventfood.model.projection.TableTypeProjection;
import by.bsu.eventfood.repository.ReservationEventRepository;
import by.bsu.eventfood.repository.ReservationPlaceRepository;
import by.bsu.eventfood.service.mapper.ReservationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static by.bsu.eventfood.util.EventFoodUtils.addHours;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private final static int RESERVATION_HOUR = 1;

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

    @Override
    public void enrichWithAvailableTables(PlaceReservationDto dto, Date date) {
        List<TableTypeProjection> reservedTables = placeRepository
                .findAllByPlaceIdAndReservationTimeBetween(dto.getPlaceId(),
                        date, addHours(date, RESERVATION_HOUR));

        if (!isEmpty(reservedTables)) {
            dto.getTypesOfTables().stream()
                    .filter(reservedTables::contains)
                    .peek(t -> t.setAvailable(false));
        }
    }

    @Override
    public void enrichWithAvailableTables(EventByIdResource dto, Date from) {
        List<TableTypeProjection> reservedTables = placeRepository
                .findAllByPlaceIdAndReservationTimeBetween(dto.getPlaceId(),
                        from, addHours(from, RESERVATION_HOUR));

        if (!isEmpty(reservedTables)) {
            dto.getTypesOfTables().stream()
                    .filter(reservedTables::contains)
                    .peek(t -> t.setAvailable(false));
        }
    }
}
