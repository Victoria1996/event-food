package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.ReservationPlace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPlaceRepository extends CrudRepository<ReservationPlace, Long> {
}
