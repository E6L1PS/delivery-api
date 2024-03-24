package ru.mai.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public record LoginDto(

        @Schema(description = "Имя пользователя", example = "admin")
        String username,

        @Schema(description = "Пароль", example = "admin")
        String password
) {
}
