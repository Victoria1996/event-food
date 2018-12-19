package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.controller.resource.EventWithPlaceResource;
import by.bsu.eventfood.controller.resource.PlaceResourceWithDescAndTime;
import by.bsu.eventfood.controller.resource.ShortEventResource;
import by.bsu.eventfood.model.Event;
import by.bsu.eventfood.model.projection.PlaceProjection;
import by.bsu.eventfood.repository.EventRepository;
import by.bsu.eventfood.service.mapper.EventMapper;
import by.bsu.eventfood.service.mapper.PlaceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static by.bsu.eventfood.util.EventFoodUtils.shortText;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public void addEvent(AddEventDto dto) {
        Event event = eventMapper.map(dto);

        eventRepository.save(event);

        log.info("Event {} was created", dto.getName());
    }

    @Override
    public void updateEvent(AddEventDto dto, Long eventId) {
        eventRepository.findById(eventId)
                .map(event -> {
                    Event eventToUpdate = eventMapper.map(dto);
                    eventToUpdate.setId(eventId);
                    eventToUpdate.setStartDate(event.getStartDate());
                    eventToUpdate.setFinishDate(event.getFinishDate());

                    log.info("Event {} ready for updating", eventId);

                    return eventToUpdate;
                })
                .ifPresent(eventRepository::save);
    }

    @Override
    public List<ShortEventResource> findPlaceEvents(Long placeId) {
        return eventRepository.findAllByPlaceId(placeId).stream()
                .map(ShortEventResource::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceResourceWithDescAndTime> getAllPlacesWithNotExpiredEvents() {
        return eventRepository.findDistinctByFinishDateAfter(new Date())
                .stream()
                .map(PlaceProjection::getPlace)
                .map(PlaceResourceWithDescAndTime::new)
                .peek(p -> p.setTime(placeMapper.mapTime(p.getTimeAsJsonString())))
                .peek(p -> p.setShortDescription(shortText(p.getDescription())))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventWithPlaceResource> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventWithPlaceResource::new)
                .collect(Collectors.toList());
    }
}
