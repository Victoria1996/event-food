package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.model.ReservationEvent;
import by.bsu.eventfood.model.ReservationPlace;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Date;

import static by.bsu.eventfood.util.EventFoodUtils.parseDate;

@Mapper(uses = DataMapper.class)
@Component
//TODO make common interface for hibernate entities.
public interface ReservationMapper {

    @Mappings({
            @Mapping(target = "place", source = "id"),
            @Mapping(target = "id", source = "id", ignore = true)})
    ReservationPlace mapPlace(ReservationDto dto);

    @Mappings({
            @Mapping(target = "event", source = "id"),
            @Mapping(target = "id", source = "id", ignore = true)})
    ReservationEvent mapEvent(ReservationDto dto);

    @AfterMapping
    default void afterMappingPlace(@MappingTarget ReservationPlace place, ReservationDto dto) {
        Date reservationDate = parseDate(dto.getDate(),
                dto.getHoursStart(), dto.getMinuteStart());

        Date currentTime = new Date();
        place.setCreated(currentTime);
        place.setModified(currentTime);
        place.setReservationTime(reservationDate);
    }

    @AfterMapping
    default void afterMappingEvent(@MappingTarget ReservationEvent event, ReservationDto dto) {
        Date reservationDate = parseDate(dto.getDate(),
                dto.getHoursStart(), dto.getMinuteStart());

        Date currentTime = new Date();
        event.setCreated(currentTime);
        event.setModified(currentTime);
        event.setReservationTime(reservationDate);
    }
}
