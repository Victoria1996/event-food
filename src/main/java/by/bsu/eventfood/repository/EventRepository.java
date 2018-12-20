package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.Event;
import by.bsu.eventfood.model.Place;
import by.bsu.eventfood.model.projection.PlaceProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByPlaceId(Long placeId);

    List<Event> findAllByClientIdAndPlaceIdIsNull(Long clientId);

    List<PlaceProjection> findDistinctByFinishDateAfter(Date date);

    @Query("select distinct e.place from Event e")
    List<Place> findPlaces();

    List<Event> findAllByFinishDateAfter(Date date);

    List<Event> findAll();
}
