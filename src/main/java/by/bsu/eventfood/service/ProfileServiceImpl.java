package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.resource.ProfileResource;
import by.bsu.eventfood.controller.resource.ShortEventResource;
import by.bsu.eventfood.controller.resource.ShortPlaceResource;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.RoleName;
import by.bsu.eventfood.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

            /**
             * Enrich with events without places.
             */
            eventRepository
                    .findAllByClientIdAndPlaceIdIsNull(clientId)
                    .forEach(event -> profileResource
                            .addEvent(new ShortEventResource(event)));
        }

        if (GENERAL_CLIENT.equals(roleName)) {
            profileResource.addAllEvents(mapToShortEvents(clientId));

            profileResource.addAllPlaces(mapToShortPlaces(clientId));
        }


        return profileResource;
    }

    private List<ShortEventResource> mapToShortEvents(Long id) {
        return reservationEventRepository
                .findAllByClientId(id)
                .stream()
                .map(re -> new ShortEventResource(re.getEvent()))
                .collect(toList());
    }

    private List<ShortPlaceResource> mapToShortPlaces(Long id) {
        return reservationPlaceRepository.
                findAllByClientId(id)
                .stream()
                .map(rp -> new ShortPlaceResource(rp.getPlace()))
                .collect(toList());
    }

    @Override
    public ProfileResource getProfileInfo(Long id) {
        return clientRepository.findById(id)
                .map(this::getCurrentProfileInfo)
                .orElse(null);
    }

    @Override
    public List<ShortPlaceResource> getClientPlaces(Client client) {
        return placeRepository
                .findAllPlaceByClientId(client.getId())
                .stream()
                .map(ShortPlaceResource::new)
                .collect(toList());
    }
}
