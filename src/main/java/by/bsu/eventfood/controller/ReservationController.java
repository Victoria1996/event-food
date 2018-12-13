package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.ReservationDto;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserve")
    public void processReservation(@RequestBody ReservationDto dto,
                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        reservationService.reserve(dto, userDetails != null ? userDetails.getClient() : null);
    }
}
