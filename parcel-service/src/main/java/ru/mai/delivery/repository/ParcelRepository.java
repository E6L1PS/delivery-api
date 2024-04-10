package ru.mai.delivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mai.delivery.model.Parcel;

/**
 * Создан: 09.04.2024.
 *
 * @author Pesternikov Danil
 */
@Repository
public interface ParcelRepository extends MongoRepository<Parcel, String> {

}
