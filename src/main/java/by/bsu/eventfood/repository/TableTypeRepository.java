package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.TableType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableTypeRepository extends CrudRepository<TableType, Long> {
}
