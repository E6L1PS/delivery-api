package ru.mai.delivery.dto;

/**
 * Создан: 23.03.2024.
 *
 * @author Pesternikov Danil
 */
public record UserInfoDto(
        String firstName,

        String lastName,

        String number,

        String email

) {
}
