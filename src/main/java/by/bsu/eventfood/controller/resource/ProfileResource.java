package by.bsu.eventfood.controller.resource;


import by.bsu.eventfood.controller.EventController;
import by.bsu.eventfood.controller.PlaceController;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
@Setter
public class ProfileResource extends ResourceSupport {
    private final String name;
    private final String email;
    private final RoleName role;
    private List<ShortEventResource> events;
    private List<ShortPlaceResource> places;


    public ProfileResource(Client client) {
        name = client.getName();
        email = client.getEmail();
        role = client.getRole().getName();

        events = new ArrayList();
        places = new ArrayList();

        add(linkTo(methodOn(PlaceController.class).addPlace(null, null)).withRel("linkToAddPlace"));
        add(linkTo(methodOn(EventController.class).addEvent(null, null)).withRel("linkToAddEvent"));
    }

    public void addEvent(ShortEventResource resource) {
        events.add(resource);
    }

    public void addPlace(ShortPlaceResource resource) {
        places.add(resource);
    }

    public void addAllEvents(List<ShortEventResource> resource) {
        events.addAll(resource);
    }

    public void addAllPlaces(List<ShortPlaceResource> resource) {
        places.addAll(resource);
    }
}
