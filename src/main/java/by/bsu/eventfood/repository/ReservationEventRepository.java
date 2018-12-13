package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.ReservationEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationEventRepository extends CrudRepository<ReservationEvent, Long> {
    List<ReservationEvent> findAllByClientId(Long id);
}
