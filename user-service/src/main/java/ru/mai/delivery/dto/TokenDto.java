package ru.mai.delivery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public record TokenDto(
        String subject,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date issuedDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date expirationTime,
        List<String> roles
) {

}