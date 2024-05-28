package ru.mai.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mai.delivery.dto.ParcelDto;
import ru.mai.delivery.model.Parcel;
import ru.mai.delivery.service.interfaces.ParcelService;

/**
 * Создан: 09.04.2024.
 *
 * @author Pesternikov Danil
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/parcel")
@RestController
public class ParcelController {

    private final ParcelService parcelService;


    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @GetMapping
    public ResponseEntity<Page<Parcel>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy
    ) {
        Page<Parcel> parcels = parcelService.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
        return ResponseEntity.ok(parcels);
    }


    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody ParcelDto parcelDto
    ) {
        parcelService.save(parcelDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public ResponseEntity<Void> update(
            @RequestBody ParcelDto parcelDto
    ) {
        parcelService.update(parcelDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") String id
    ) {
        parcelService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
