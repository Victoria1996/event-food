package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.Event;
import by.bsu.eventfood.model.projection.PlaceProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByPlaceId(Long placeId);

    List<PlaceProjection> findEventsByFinishDateAfter(Date date);
}
