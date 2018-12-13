package by.bsu.eventfood.controller.resource;


import by.bsu.eventfood.controller.EventController;
import by.bsu.eventfood.controller.PlaceController;
import by.bsu.eventfood.model.*;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

import static by.bsu.eventfood.util.EventFoodUtils.isDateExpired;
import static java.lang.String.valueOf;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ProfileResource extends ResourceSupport {
    private final String name;
    private final String email;
    private final RoleName role;
    private List<ShortEventResource> events;
    private List<ShortPlaceResource> places;


    @Getter
    public static class ShortEventResource extends ResourceSupport {
        private final String name;
        private final Boolean isEnded;

        public ShortEventResource(Event event) {
            Long id = event.getId();
            name = event.getEventName();
            isEnded = isDateExpired(event.getFinishDate());

            add(linkTo(methodOn(EventController.class).getEvent(valueOf(id))).withRel("link"));
            add(linkTo(methodOn(EventController.class).updateEvent(null, id)).withRel("updateLink"));
        }
    }

    @Getter
    public static class ShortPlaceResource extends ResourceSupport {
        private final String name;
        private final String address;

        public ShortPlaceResource(Place place) {
            Long id = place.getId();
            name = place.getName();
            address = place.getAddress();

            add(linkTo(methodOn(PlaceController.class).getPlace(valueOf(id))).withRel("link"));
            add(linkTo(methodOn(PlaceController.class).updatePlace(null, id)).withRel("updateLink"));
        }
    }

    public ProfileResource(Client client) {
        name = client.getName();
        email = client.getEmail();
        role = client.getRole().getName();

        events = new ArrayList();
        places = new ArrayList();

        add(linkTo(methodOn(PlaceController.class).addPlace(null, null)).withRel("linkToAddPlace"));
        add(linkTo(methodOn(EventController.class).addEvent(null)).withRel("linkToAddEvent"));
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
