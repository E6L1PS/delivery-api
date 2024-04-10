package ru.mai.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mai.delivery.dto.DeliveryDto;
import ru.mai.delivery.mapper.DeliveryMapper;
import ru.mai.delivery.model.Delivery;
import ru.mai.delivery.model.Location;
import ru.mai.delivery.repository.DeliveryRepository;
import ru.mai.delivery.service.interfaces.DeliveryService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final DeliveryMapper deliveryMapper;

    @Override
    public Page<Delivery> findAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    @Override
    public List<Delivery> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void save(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.deliveryDtoToDelivery(deliveryDto);
        delivery.setLocations(Collections.singletonList(
                Location.builder()
                        .currentLocation(deliveryDto.currentLocation())
                        .receiptDate(LocalDate.now())
                        .build()
        ));
        deliveryRepository.save(delivery);
    }

    @Override
    public void update(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.deliveryDtoToDelivery(deliveryDto);
        deliveryRepository.save(delivery);
    }

    @Override
    public void delete(String id) {
        deliveryRepository.deleteById(id);
    }

}
