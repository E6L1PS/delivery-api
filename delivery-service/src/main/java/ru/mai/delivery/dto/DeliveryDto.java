package ru.mai.delivery.dto;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
public record DeliveryDto(

        Long senderId,
        Long recipientId,
        String currentLocation
) {
}
