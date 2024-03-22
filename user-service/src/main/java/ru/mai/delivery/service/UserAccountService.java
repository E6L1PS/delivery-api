package ru.mai.delivery.service;

import ru.mai.delivery.dto.LoginDto;
import ru.mai.delivery.dto.RegisterDto;
import ru.mai.delivery.dto.TokenDto;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public interface UserAccountService {

    void register(RegisterDto registerDto);

    TokenDto authenticate(LoginDto loginDto);

}
