package ru.mai.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mai.delivery.dto.CurrentLocationDto;
import ru.mai.delivery.dto.DeliveryDto;
import ru.mai.delivery.model.Delivery;
import ru.mai.delivery.service.interfaces.DeliveryService;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
@RestController
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @GetMapping
    public ResponseEntity<Page<Delivery>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "status") String sortBy
    ) {
        return ResponseEntity.ok(deliveryService.findAll(PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @GetMapping("/sender/{senderId}")
    public ResponseEntity<?> findAllBySenderId(
            @PathVariable(name = "senderId") Long senderId
    ) {
        return ResponseEntity.ok(deliveryService.findAllBySenderId(senderId));
    }

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> findAllByRecipientId(
            @PathVariable(name = "recipientId") Long recipientId
    ) {
        return ResponseEntity.ok(deliveryService.findAllByRecipientId(recipientId));
    }

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody DeliveryDto deliveryDto
    ) {
        deliveryService.save(deliveryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public ResponseEntity<Void> update(
            @RequestBody DeliveryDto deliveryDto
    ) {
        deliveryService.update(deliveryDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") String id
    ) {
        deliveryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/location")
    public ResponseEntity<Void> changeCurrentLocation(
            @RequestBody CurrentLocationDto currentLocationDto
    ) {
        deliveryService.changeCurrentLocation(currentLocationDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
