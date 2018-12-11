package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.model.ReservationEvent;
import by.bsu.eventfood.model.ReservationPlace;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.Date;

import static by.bsu.eventfood.util.EventFoodUtils.parseDate;

@Mapper(uses = DataMapper.class)
@Component
public interface ReservationMapper {

    @Mapping(target = "place", source = "id")
    ReservationPlace mapPlace(ReservationDto dto);

    @Mapping(target = "event", source = "id")
    ReservationEvent mapEvent(ReservationDto dto);

    @AfterMapping
    //TODO refacto
    default void afterMappingPlace(@MappingTarget ReservationPlace place, AddEventDto dto) {
        Date reservationDate = parseDate(dto.getDate(),
                dto.getHoursStart(), dto.getMinuteStart());

        Date currentTime = new Date();
        place.setCreated(currentTime);
        place.setModified(currentTime);
        place.setReservationTime(reservationDate);
    }

    @AfterMapping

    //TODO refactor
    default void afterMappingEvent(@MappingTarget ReservationEvent event, AddEventDto dto) {
        Date reservationDate = parseDate(dto.getDate(),
                dto.getHoursStart(), dto.getMinuteStart());

        Date currentTime = new Date();
        event.setCreated(currentTime);
        event.setModified(currentTime);
        event.setReservationTime(reservationDate);
    }
}
