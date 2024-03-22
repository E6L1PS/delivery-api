package ru.mai.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mai.delivery.dto.LoginDto;
import ru.mai.delivery.dto.RegisterDto;
import ru.mai.delivery.dto.TokenDto;
import ru.mai.delivery.service.UserAccountService;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
@RestController
public class UserAccountController {

    public final UserAccountService userAccountService;

    @PostMapping("/reg")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterDto registerDto
    ) {
        userAccountService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> authenticate(
            @Valid @RequestBody LoginDto loginDto
    ) {
        TokenDto tokenDto = userAccountService.authenticate(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

}
