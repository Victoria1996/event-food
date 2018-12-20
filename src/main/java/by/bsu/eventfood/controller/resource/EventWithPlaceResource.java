package by.bsu.eventfood.controller.resource;

import by.bsu.eventfood.model.Event;
import lombok.Getter;

@Getter
public class EventWithPlaceResource {
    public EventWithPlaceResource(Event event) {
        this.event = new ShortEventResource(event);
        if (event.getPlace() != null) {
            place = new ShortPlaceResource(event.getPlace());
        }
    }

    private ShortEventResource event;
    private ShortPlaceResource place;
}
