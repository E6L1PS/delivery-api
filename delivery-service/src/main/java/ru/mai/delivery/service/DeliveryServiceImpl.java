package ru.mai.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mai.delivery.dto.CurrentLocationDto;
import ru.mai.delivery.dto.DeliveryDto;
import ru.mai.delivery.mapper.DeliveryMapper;
import ru.mai.delivery.model.Delivery;
import ru.mai.delivery.model.Location;
import ru.mai.delivery.repository.DeliveryRepository;
import ru.mai.delivery.service.interfaces.DeliveryService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Delivery> findAllBySenderId(Long senderId) {
        return deliveryRepository.findBySenderId(senderId);
    }

    @Override
    public List<Delivery> findAllByRecipientId(Long recipientId) {
        return deliveryRepository.findByRecipientId(recipientId);
    }

    @Override
    public void save(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.deliveryDtoToDelivery(deliveryDto);
        delivery.setLocations(Collections.singletonList(
                Location.builder()
                        .currentLocation(deliveryDto.currentLocation())
                        .receiptDateTime(LocalDateTime.now())
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
    public void changeCurrentLocation(CurrentLocationDto currentLocationDto) {
        Delivery delivery = deliveryRepository.findById(currentLocationDto.id())
                .orElseThrow(() -> new NoSuchElementException("not found document"));
        List<Location> locations = delivery.getLocations();
        locations.add(
                Location.builder()
                        .currentLocation(currentLocationDto.location())
                        .receiptDateTime(LocalDateTime.now())
                        .build()
        );
        delivery.setLocations(locations);
        deliveryRepository.save(delivery);
    }

    @Override
    public void delete(String id) {
        deliveryRepository.deleteById(id);
    }

}
