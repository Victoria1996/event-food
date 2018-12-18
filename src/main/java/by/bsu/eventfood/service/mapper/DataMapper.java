package by.bsu.eventfood.service.mapper;


import by.bsu.eventfood.model.*;
import by.bsu.eventfood.repository.EventRepository;
import by.bsu.eventfood.repository.PlaceRepository;
import by.bsu.eventfood.repository.RoleRepository;
import by.bsu.eventfood.repository.TableTypeRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Mapper
@Component
public abstract class DataMapper {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TableTypeRepository tableTypeRepository;

    public Role mapRoleName(RoleName roleName) {
        return roleName == null ? null :
                roleRepository.findByName(roleName)
                        .orElseThrow(NoSuchElementException::new);
    }

    public Place mapPlaceId(Long placeId) {
        return placeId == null ? null : placeRepository.findById(placeId)
                .orElseThrow(NoSuchElementException::new);
    }

    public Event mapEventId(Long eventId) {
        return eventId == null ? null :
                eventRepository.findById(eventId)
                        .orElseThrow(NoSuchElementException::new);
    }

    public TableType mapTableType(Long tableTypeId) {
        return tableTypeId == null ? null :
                tableTypeRepository.findById(tableTypeId)
                        .orElseThrow(NoSuchElementException::new);
    }
}
