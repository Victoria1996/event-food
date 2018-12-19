package by.bsu.eventfood.controller.resource;

import by.bsu.eventfood.controller.dto.TableTypeDto;
import by.bsu.eventfood.model.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class EventByIdResource extends EventWithPlaceResource {
    private Date dateTimeStart;
    private String description;

    private String userName;
    private String userPhoneNumber;

    private List<TableTypeDto> typesOfTables;

    @JsonIgnore
    private Long placeId;

    public EventByIdResource(Event event) {
        super(event);
        dateTimeStart = event.getStartDate();
        description = event.getEventDescription();
        placeId = event.getPlace().getId();
    }
}
