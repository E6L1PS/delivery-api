package ru.mai.delivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mai.delivery.model.Delivery;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

}
