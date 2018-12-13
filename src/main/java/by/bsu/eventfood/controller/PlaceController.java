package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.dto.ResponseDto;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.PlaceService;
import by.bsu.eventfood.service.RoleActionUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static by.bsu.eventfood.service.RoleActionUrlResolver.ActionUrl.ADD_PLACE;

@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

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
    public ResponseEntity getPlace(@PathVariable String id) {

        return ResponseEntity.ok().build();
    }
}
