package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.resource.ProfileResource;
import by.bsu.eventfood.controller.resource.ShortPlaceResource;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping
    public ProfileResource getCurrentProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return profileService.getCurrentProfileInfo(userDetails.getClient());
    }

    @GetMapping("/{id}")
    public ProfileResource getProfile(@PathVariable Long id) {
        return profileService.getProfileInfo(id);
    }

    @GetMapping("/add-event")
    public List<ShortPlaceResource> getBusinessClientPlaces(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return profileService.getClientPlaces(userDetails.getClient());
    }
}
