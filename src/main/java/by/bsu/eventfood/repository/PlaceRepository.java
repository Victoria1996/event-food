package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {
    List<Place> findAllPlaceByClientId(Long clientId);
}
