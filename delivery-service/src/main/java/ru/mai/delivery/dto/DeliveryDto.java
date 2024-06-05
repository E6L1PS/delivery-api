package ru.mai.delivery.dto;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
public record DeliveryDto(
        String id,
        String parcelId,
        Long senderId,
        Long recipientId,
        String currentLocation
) {
}
