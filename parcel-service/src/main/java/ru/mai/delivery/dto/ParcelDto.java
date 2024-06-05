package ru.mai.delivery.dto;

/**
 * Создан: 09.04.2024.
 *
 * @author Pesternikov Danil
 */
public record ParcelDto(
        String id,
        String description,
        String weight,
        String dimensions

) {
}
