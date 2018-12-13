package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.dto.TableTypeDto;
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

import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper
@Component
@Slf4j
public abstract class PlaceMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Mappings({
            @Mapping(target = "placeNumber", source = "fullNumberOfPlaces")
    })
    public abstract Place map(AddPlaceDto addPlaceDto);

    public String mapWorkingHours(List<WorkingHourDto> workingHours) {
        return mapListToJsonString(workingHours);
    }

    public String mapTypesOfTables(List<TableTypeDto> typesOfTables) {
        return mapListToJsonString(typesOfTables);
    }

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
