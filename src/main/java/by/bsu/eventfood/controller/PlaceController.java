package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.dto.ResponseDto;
import by.bsu.eventfood.controller.resource.PlaceResourceById;
import by.bsu.eventfood.controller.resource.PlaceResourceWithDescAndTime;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.EventService;
import by.bsu.eventfood.service.PlaceService;
import by.bsu.eventfood.service.RoleActionUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.bsu.eventfood.service.RoleActionUrlResolver.ActionUrl.ADD_PLACE;

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
    public ResponseEntity getReservePlace(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<PlaceResourceWithDescAndTime> getAllPlaces() {
        return eventService.getAllPlacesWithNotExpiredEvents();
    }
}
