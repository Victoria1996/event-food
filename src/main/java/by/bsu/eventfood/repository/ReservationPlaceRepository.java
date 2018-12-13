package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.ReservationPlace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationPlaceRepository extends CrudRepository<ReservationPlace, Long> {
    List<ReservationPlace> findAllByClientId(Long id);
}
