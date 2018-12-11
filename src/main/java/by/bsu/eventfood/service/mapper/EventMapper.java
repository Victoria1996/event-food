package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.model.Event;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Date;

import static by.bsu.eventfood.util.EventFoodUtils.addRandomDays;
import static by.bsu.eventfood.util.EventFoodUtils.parseDate;

@Mapper(uses = DataMapper.class)
@Component
public interface EventMapper {
    @Mappings({
            @Mapping(target = "place", source = "placeId"),
            @Mapping(target = "eventNumber", source = "numberOfAllPlaces"),
            @Mapping(target = "eventRating", source = "rating"),
            @Mapping(target = "eventName", source = "name"),
            @Mapping(target = "eventDescription", source = "description"),
    })
    Event map(AddEventDto addEventDto);

    @AfterMapping
    default void afterMapping(@MappingTarget Event event, AddEventDto dto) {
        Date startDate = parseDate(dto.getDate(),
                dto.getHoursStart(), dto.getMinuteStart());
        Date finishDate = addRandomDays(startDate);

        event.setStartDate(startDate);
        event.setFinishDate(finishDate);
    }
}
