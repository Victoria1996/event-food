package by.bsu.eventfood.controller.resource;

import by.bsu.eventfood.controller.EventController;
import by.bsu.eventfood.model.Event;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static by.bsu.eventfood.util.EventFoodUtils.isDateExpired;
import static java.lang.String.valueOf;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class ShortEventResource extends ResourceSupport {
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
