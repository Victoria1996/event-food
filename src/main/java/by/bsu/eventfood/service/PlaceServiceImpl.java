package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.Place;
import by.bsu.eventfood.repository.PlaceRepository;
import by.bsu.eventfood.service.mapper.PlaceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private PlaceRepository placeRepository;

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
}
