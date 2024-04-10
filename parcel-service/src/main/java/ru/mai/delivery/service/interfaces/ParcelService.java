package ru.mai.delivery.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mai.delivery.dto.ParcelDto;
import ru.mai.delivery.model.Parcel;

/**
 * Создан: 09.04.2024.
 *
 * @author Pesternikov Danil
 */
public interface ParcelService {

    Page<Parcel> findAll(Pageable pageable);

    Parcel findById(Long id);

    void save(ParcelDto parcelDto);

    void update(ParcelDto parcelDto);

    void delete(String id);


}
