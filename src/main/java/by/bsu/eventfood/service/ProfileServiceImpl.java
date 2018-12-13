package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.resource.ProfileResource;
import by.bsu.eventfood.controller.resource.ProfileResource.ShortEventResource;
import by.bsu.eventfood.controller.resource.ProfileResource.ShortPlaceResource;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.RoleName;
import by.bsu.eventfood.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static by.bsu.eventfood.model.RoleName.BUSINESS_CLIENT;
import static by.bsu.eventfood.model.RoleName.GENERAL_CLIENT;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ReservationEventRepository reservationEventRepository;

    @Autowired
    private ReservationPlaceRepository reservationPlaceRepository;

    @Override
    public ProfileResource getCurrentProfileInfo(Client client) {
        Long clientId = client.getId();
        RoleName roleName = client.getRole().getName();

        final ProfileResource profileResource = new ProfileResource(client);

        if (BUSINESS_CLIENT.equals(roleName)) {
            placeRepository
                    .findAllPlaceByClientId(clientId)
                    .forEach(place -> {
                        profileResource
                                .addPlace(new ShortPlaceResource(place));

                        eventRepository
                                .findAllByPlaceId(place.getId())
                                .forEach(event -> profileResource
                                        .addEvent(new ShortEventResource(event)));
                    });
        }

        if (GENERAL_CLIENT.equals(roleName)) {

            profileResource.addAllEvents(reservationEventRepository
                    .findAllByClientId(clientId)
                    .stream()
                    .map(re -> new ShortEventResource(re.getEvent()))
                    .collect(toList()));

            profileResource.addAllPlaces(reservationPlaceRepository.
                    findAllByClientId(clientId)
                    .stream()
                    .map(rp -> new ShortPlaceResource(rp.getPlace()))
                    .collect(toList()));
        }


        return profileResource;
    }

    @Override
    public ProfileResource getProfileInfo(Long id) {
        return clientRepository.findById(id)
                .map(ProfileResource::new)
                .orElse(null);
    }
}
