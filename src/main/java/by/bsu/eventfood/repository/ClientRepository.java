package by.bsu.eventfood.repository;

import by.bsu.eventfood.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByEmail(String email);

    Boolean existsByEmail(String email);
}
