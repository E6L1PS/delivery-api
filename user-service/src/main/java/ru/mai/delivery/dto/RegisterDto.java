package ru.mai.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public record RegisterDto(

        @Size(max = 50, min = 5, message = "Имя должно быть от 5 до 50 символов")
        @Schema(description = "Имя")
        String firstName,

        @Size(max = 50, min = 5, message = "Фамилия должна быть от 5 до 50 символов")
        @Schema(description = "Фамилия")
        String lastName,

        @Pattern(regexp = "^[0-9]{5,20}$", message = "Неверный формат номера")
        @Size(max = 20, min = 5, message = "Номер должен быть от 5 до 20 символов")
        @Schema(description = "Номер телефона", example = "79221110500")
        String number,

        @NotNull
        @NotBlank(message = "email не должен быть пустым")
        @Email(message = "Неверный формат email")
        @Schema(description = "email", example = "email@email.ru")
        String email,

        @Size(max = 255, min = 5, message = "Имя пользователя должно быть от 5 до 255 символов")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-_=+;:,.<>?]+$", message = "Имя пользователя должно содержать только английские буквы, цифры и специальные символы")
        String username,

        @Size(max = 255, min = 5, message = "Пароль должен быть от 5 до 255 символов")
        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-_=+;:,.<>?]+$", message = "Пароль должен содержать только английские буквы, цифры и специальные символы")
        String password

) {
}
