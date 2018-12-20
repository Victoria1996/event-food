package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.dto.PlaceReservationDto;
import by.bsu.eventfood.controller.dto.ResponseDto;
import by.bsu.eventfood.controller.resource.PlaceResourceById;
import by.bsu.eventfood.controller.resource.PlaceResourceWithDescAndTime;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.EventService;
import by.bsu.eventfood.service.PlaceService;
import by.bsu.eventfood.service.RoleActionUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static by.bsu.eventfood.service.RoleActionUrlResolver.ActionUrl.ADD_PLACE;
import static by.bsu.eventfood.util.EventFoodUtils.parseDate;

@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @Autowired
    private RoleActionUrlResolver urlResolver;

    @PostMapping("/add")
    public ResponseDto addPlace(@RequestBody AddPlaceDto addPlaceDto,
                                @AuthenticationPrincipal CustomUserDetails details) {
        Client client = details.getClient();
        placeService.addPlace(addPlaceDto, client);

        return ResponseDto.builder()
                .status("success")
                .url(urlResolver.resolve(ADD_PLACE, client))
                .build();
    }

    @PatchMapping("/update/{placeId}")
    public ResponseEntity updatePlace(@RequestBody AddPlaceDto addEventDto,
                                      @PathVariable Long placeId) {
        placeService.updatePlace(addEventDto, placeId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public PlaceResourceById getPlace(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return placeService.getPlaceById(id, customUserDetails);
    }

    @GetMapping("/{id}/reserve")
    public PlaceReservationDto getReservePlace(
            @PathVariable Long id,
            @RequestParam("hours") int hours,
            @RequestParam("minutes") int minutes,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam("date") Date date,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PlaceReservationDto placeReservation = placeService.getPlaceReservation(id, parseDate(date, hours, minutes));

        if (placeReservation != null && customUserDetails != null) {
            placeReservation.setUserName(customUserDetails.getClient().getName());
            placeReservation.setUserPhoneNumber(customUserDetails.getClient().getEmail());
        }

        return placeReservation;
    }

    @GetMapping("/all")
    public List<PlaceResourceWithDescAndTime> getAllPlaces(@RequestParam boolean hasActiveEvents) {
        return eventService.getAllPlaces(hasActiveEvents);
    }
}
