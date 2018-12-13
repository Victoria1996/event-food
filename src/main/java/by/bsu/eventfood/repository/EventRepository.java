package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByPlaceId(Long placeId);
}
