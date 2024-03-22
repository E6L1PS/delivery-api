package ru.mai.delivery.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public record RegisterDto(

        @Size(max = 255, min = 5, message = "Имя пользователя должно быть от 5 до 255 символов")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-_=+;:,.<>?]+$", message = "Имя пользователя должно содержать только английские буквы, цифры и специальные символы")
        String username,

        @Size(max = 255, min = 5, message = "Пароль должен быть от 5 до 255 символов")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-_=+;:,.<>?]+$", message = "Пароль должен содержать только английские буквы, цифры и специальные символы")
        String password

) {
}
