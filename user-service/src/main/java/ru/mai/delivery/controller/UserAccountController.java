package ru.mai.delivery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mai.delivery.dto.RegisterDto;
import ru.mai.delivery.dto.TokenDto;
import ru.mai.delivery.dto.UserInfoDto;
import ru.mai.delivery.model.UserAccount;
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

    @Operation(description = "Поиск по id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/{id}")
    public ResponseEntity<UserAccount> searchUserById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userAccountService.searchUsersById(id));
    }

    @Operation(description = "Поиск по маске")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public ResponseEntity<Page<UserInfoDto>> searchUsersByMask(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        Page<UserInfoDto> userInfoDtos = userAccountService.searchUsersByMask(
                String.format("%%%s%%", firstName),
                String.format("%%%s%%", lastName),
                PageRequest.of(page, size, Sort.by("firstName").descending())
        );
        return ResponseEntity.ok(userInfoDtos);
    }

    @Operation(description = "Регистрация нового пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reg")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterDto registerDto
    ) {
        userAccountService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Аутентификация пользователя")
    @SecurityRequirement(name = "basicAuth")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth")
    public ResponseEntity<TokenDto> authenticate(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        TokenDto tokenDto = userAccountService.authenticate(userDetails);
        return ResponseEntity.ok(tokenDto);
    }

    @Operation(description = "Обновление данных пользователя (текущего пользователя, который авторизован) {'ROLE_USER', 'ROLE_ADMIN'}")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public ResponseEntity<Void> updateUserInfo(
            @RequestBody UserInfoDto userInfoDto,
            @AuthenticationPrincipal UserAccount userAccount
    ) {
        userAccountService.updateUserInfo(userInfoDto, userAccount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(description = "Обновление данных пользователя по id. {'ROLE_ADMIN'}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> updateUserInfoById(
            @PathVariable(name = "id") Long id,
            @RequestBody UserInfoDto userInfoDto
    ) {
        userAccountService.updateUserInfoById(id, userInfoDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(description = "Удаление пользователя по id. {'ROLE_ADMIN'}")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public ResponseEntity<Void> deleteUserByUsername(
            @RequestParam("username") String username
    ) {
        userAccountService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
