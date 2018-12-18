package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.ReservationPlace;
import by.bsu.eventfood.model.projection.TableTypeProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationPlaceRepository extends CrudRepository<ReservationPlace, Long> {
    List<ReservationPlace> findAllByClientId(Long id);

    List<TableTypeProjection> findAllByPlaceIdAndReservationTimeBetween(Long placeId, Date from, Date to);
}
