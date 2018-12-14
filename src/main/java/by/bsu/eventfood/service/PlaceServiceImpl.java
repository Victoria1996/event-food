package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.resource.PlaceResourceWithDescAndTime;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.Place;
import by.bsu.eventfood.model.projection.PlaceProjection;
import by.bsu.eventfood.repository.EventRepository;
import by.bsu.eventfood.repository.PlaceRepository;
import by.bsu.eventfood.service.mapper.PlaceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
    private final static int SHORT_TEXT_LENGTH = 300;
    private final static String SHORT_TEX_ENDS_POSTIFX = "...";

    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void addPlace(AddPlaceDto addPlaceDto, Client client) {
        Place place = placeMapper.map(addPlaceDto);
        place.setClient(client);

        placeRepository.save(place);

        log.info("Place {} was created", addPlaceDto.getName());
    }

    @Override
    public void updatePlace(AddPlaceDto dto, Long placeId) {
        placeRepository.findById(placeId)
                .map(place -> {
                    Place placeToUpdate = placeMapper.map(dto);
                    placeToUpdate.setId(placeId);

                    log.info("Place {} ready for updating", placeId);

                    return placeToUpdate;
                })
                .ifPresent(placeRepository::save);
    }

    @Override
    public List<PlaceResourceWithDescAndTime> getAllPlaces() {
        return eventRepository.findEventsByFinishDateAfter(new Date())
                .stream()
                .map(PlaceProjection::getPlace)
                .map(PlaceResourceWithDescAndTime::new)
                .peek(p -> p.setTime(placeMapper.mapTime(p.getTimeAsJsonString())))
                .peek(p -> p.setShortDescription(shortText(p.getDescription())))
                .collect(Collectors.toList());
    }

    private String shortText(String text) {
        if (text == null) {
            return null;
        }

        if (text.length() < SHORT_TEXT_LENGTH) {
            return text;
        }

        return text.substring(0, Math.min(text.length(), SHORT_TEXT_LENGTH)) + SHORT_TEX_ENDS_POSTIFX;
    }
}
