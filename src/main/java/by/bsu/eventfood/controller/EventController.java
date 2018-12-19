package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.controller.resource.EventWithPlaceResource;
import by.bsu.eventfood.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody AddEventDto addEventDto) {
        eventService.addEvent(addEventDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{eventId}")
    public ResponseEntity updateEvent(@RequestBody AddEventDto addEventDto,
                                      @PathVariable Long eventId) {
        eventService.updateEvent(addEventDto, eventId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent(@PathVariable String id) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<EventWithPlaceResource> getEvents() {
        return eventService.getAllEvents();
    }

}
