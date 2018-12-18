package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddPlaceDto;
import by.bsu.eventfood.controller.dto.CommentDto;
import by.bsu.eventfood.controller.dto.PlaceReservationDto;
import by.bsu.eventfood.controller.resource.PlaceResourceById;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.model.Place;
import by.bsu.eventfood.repository.PlaceRepository;
import by.bsu.eventfood.repository.TableTypeRepository;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.mapper.PlaceMapper;
import by.bsu.eventfood.service.mapper.TableTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static by.bsu.eventfood.util.EventFoodUtils.shortText;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TableTypeRepository tableTypeRepository;

    @Autowired
    private TableTypeMapper tableTypeMapper;

    @Override
    @Transactional
    public void addPlace(AddPlaceDto addPlaceDto, Client client) {
        Place place = placeMapper.map(addPlaceDto);

        place.setClient(client);

        placeRepository.save(place);

        addPlaceDto.getTypesOfTables()
                .stream()
                .map(tableTypeMapper::map)
                .peek(t -> t.setPlace(place))
                .forEach(tableTypeRepository::save);


        log.info("Place {} was created", addPlaceDto.getName());
    }

    @Override
    @Transactional
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
    public PlaceResourceById getPlaceById(Long id, CustomUserDetails customUserDetails) {
        return placeRepository.findById(id)
                .map(p -> {
                    Long placeId = p.getId();

                    PlaceResourceById resource = new PlaceResourceById(p);
                    resource.setTime(placeMapper.mapTime(resource.getTimeAsJsonString()));
                    resource.setShortDescription(shortText(p.getDescription()));
                    resource.setEvents(eventService.findPlaceEvents(placeId));

                    List<CommentDto> placeComments = commentService.findPlaceComments(placeId);
                    if (customUserDetails != null) {
                        enrichCommentsWithCurrentUser(placeComments, customUserDetails.getClient().getId());
                    }
                    resource.setComments(placeComments);

                    return resource;
                })
                .orElse(null);
    }

    @Override
    public PlaceReservationDto getPlaceReservation(Long id, Date date) {
        PlaceReservationDto dto = placeRepository.findById(id)
                .map(placeMapper::map)
                .orElse(null);

        if (dto != null) {
            reservationService.enrichWithAvailableTables(dto, date);
        }

        return dto;
    }

    private void enrichCommentsWithCurrentUser(List<CommentDto> comments, Long clientId) {
        comments.stream()
                .filter(c -> clientId.equals(c.getClientId()))
                .peek(c -> c.setIsCurrentUser(true));
    }
}
