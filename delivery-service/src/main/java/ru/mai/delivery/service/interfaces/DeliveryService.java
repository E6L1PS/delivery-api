package ru.mai.delivery.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mai.delivery.dto.CurrentLocationDto;
import ru.mai.delivery.dto.DeliveryDto;
import ru.mai.delivery.model.Delivery;

import java.util.List;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
public interface DeliveryService {

    Page<Delivery> findAll(Pageable pageable);

    List<Delivery> findAllBySenderId(Long senderId);

    List<Delivery> findAllByRecipientId(Long recipientId);

    void save(DeliveryDto deliveryDto);

    void update(DeliveryDto deliveryDto);

    void changeCurrentLocation(CurrentLocationDto currentLocationDto);

    void delete(String id);

}
