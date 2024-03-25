package ru.mai.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mai.delivery.dto.LoginDto;
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

    @PreAuthorize("hasAnyAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @PutMapping
    public ResponseEntity<?> updateUserInfo(
            @RequestBody UserInfoDto userInfoDto,
            @AuthenticationPrincipal UserAccount userAccount
    ) {
        userAccountService.updateUserInfo(userInfoDto, userAccount);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<?> deleteUserByUsername(
            @RequestParam("username") String username
    ) {
        userAccountService.deleteUserByUsername(username);
        return ResponseEntity.ok().build();
    }

}
