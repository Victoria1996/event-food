package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.controller.resource.EventByIdResource;
import by.bsu.eventfood.controller.resource.EventWithPlaceResource;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static by.bsu.eventfood.util.EventFoodUtils.parseDate;

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
    public EventByIdResource getEvent(@PathVariable Long id,
                                      @RequestParam("hours") int hours,
                                      @RequestParam("minutes") int minutes,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd")
                                      @RequestParam("date") Date date,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        EventByIdResource resource = eventService.findEvent(id, parseDate(date, hours, minutes));

        if (resource != null && customUserDetails != null) {
            resource.setUserName(customUserDetails.getClient().getName());
            resource.setUserPhoneNumber(customUserDetails.getClient().getEmail());
        }

        return resource;
    }

    @GetMapping("/all")
    public List<EventWithPlaceResource> getEvents(@RequestParam boolean isEnded) {
        return eventService.getAllEvents(isEnded);
    }

}
