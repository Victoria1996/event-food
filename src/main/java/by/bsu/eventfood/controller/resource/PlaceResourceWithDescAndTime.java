package by.bsu.eventfood.controller.resource;

import by.bsu.eventfood.controller.dto.WorkingHourDto;
import by.bsu.eventfood.model.Place;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@JsonIgnoreProperties({"id", "timeAsJsonString"})
public class PlaceResourceWithDescAndTime extends ShortPlaceResource {
    @Setter
    private String shortDescription;

    @Setter
    private List<WorkingHourDto> time;

    private String description;
    private String timeAsJsonString;

    public PlaceResourceWithDescAndTime(Place place) {
        super(place);

        description = place.getDescription();
        timeAsJsonString = place.getWorkingHours();
    }
}
