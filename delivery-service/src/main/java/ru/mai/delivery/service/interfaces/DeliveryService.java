package ru.mai.delivery.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<Delivery> findAllByUserId(Long userId);

    void save(DeliveryDto deliveryDto);

    void update(DeliveryDto deliveryDto);

    void delete(String id);

}
