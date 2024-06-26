package ru.mai.delivery.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Delivery OpenAPI Specification",
                version = "1.0",
                contact = @Contact(
                        name = "Pesternikov Danil",
                        url = "https://t.me/gnitfihsnwod",
                        email = "danils003@mail.ru"
                ),
                license = @License(
                        name = "Apache License 2.0",
                        url = "http://www.apache.org/licenses/"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenApiConfig {
}
