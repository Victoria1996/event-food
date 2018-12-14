package by.bsu.eventfood.controller.resource;

import by.bsu.eventfood.controller.PlaceController;
import by.bsu.eventfood.model.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static java.lang.String.valueOf;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ShortPlaceResource extends ResourceSupport {
    private final String name;
    private final String address;

    @JsonProperty("id")
    private final Long placeId;

    public ShortPlaceResource(Place place) {
        Long id = place.getId();
        name = place.getName();
        address = place.getAddress();
        this.placeId = id;

        add(linkTo(methodOn(PlaceController.class).getPlace(valueOf(id))).withRel("link"));
        add(linkTo(methodOn(PlaceController.class).updatePlace(null, id)).withRel("updateLink"));
    }
}
