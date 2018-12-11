package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.AddEventDto;
import by.bsu.eventfood.model.Event;
import by.bsu.eventfood.repository.EventRepository;
import by.bsu.eventfood.service.mapper.EventMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

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
}
