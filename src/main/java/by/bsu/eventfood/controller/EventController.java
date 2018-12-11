package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public void addEvent(@RequestBody AddEventDto addEventDto) {
        eventService.addEvent(addEventDto);
    }

    @PatchMapping("/update/{eventId}")
    public void updateEvent(@RequestBody AddEventDto addEventDto,
                            @PathVariable Long eventId) {
        eventService.updateEvent(addEventDto, eventId);
    }
}
