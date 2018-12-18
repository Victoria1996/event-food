package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.dto.PlaceReservationDto;
import by.bsu.eventfood.controller.dto.WorkingHourDto;
import by.bsu.eventfood.model.Place;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper(uses = TableTypeMapper.class)
@Component
@Slf4j
public abstract class PlaceMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Mappings({
            @Mapping(target = "placeNumber", source = "fullNumberOfPlaces"),
            @Mapping(ignore = true, target = "typesOfTables")
    })
    public abstract Place map(AddPlaceDto addPlaceDto);

    public String mapWorkingHours(List<WorkingHourDto> workingHours) {
        return mapListToJsonString(workingHours);
    }

    public List<WorkingHourDto> mapTime(String workingHours) {
        List<WorkingHourDto> workingHourDtos = null;

        try {
            workingHourDtos = objectMapper.readValue(workingHours, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, WorkingHourDto.class));
        } catch (IOException e) {
            log.error("Failed map json string to object {}", e.getMessage());
        }

        return workingHourDtos;
    }

    @Mappings({
            @Mapping(target = "placeId", source = "id"),
            @Mapping(target = "placeName", source = "name")
    })
    public abstract PlaceReservationDto map(Place place);

    private String mapListToJsonString(List dtos) {
        String result = EMPTY;

        if (!isEmpty(dtos)) {
            try {
                result = objectMapper.writeValueAsString(dtos);
            } catch (JsonProcessingException e) {
                log.error("Failed map list to string {}", e.getMessage());
            }
        }

        return result;
    }
}
