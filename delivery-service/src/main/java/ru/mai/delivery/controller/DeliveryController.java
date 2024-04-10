package ru.mai.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<Delivery>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "status") String sortBy
    ) {
        return ResponseEntity.ok(deliveryService.findAll(PageRequest.of(page, size, Sort.by(sortBy))));
    }


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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {
        deliveryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
