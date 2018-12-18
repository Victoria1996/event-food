package by.bsu.eventfood.controller.resource;


import by.bsu.eventfood.controller.PlaceController;
import by.bsu.eventfood.controller.dto.CommentDto;
import by.bsu.eventfood.model.Place;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class PlaceResourceById extends PlaceResourceWithDescAndTime {
    @Setter
    private List<ShortEventResource> events;

    @Setter
    private List<CommentDto> comments;

    public PlaceResourceById(Place place) {
        super(place);

        add(linkTo(methodOn(PlaceController.class)
                .getReservePlace(place.getId(), 0, 0, null, null)).withRel("reserverLink"));
    }
}
