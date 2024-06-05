package ru.mai.delivery.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mai.delivery.dto.RegisterDto;
import ru.mai.delivery.dto.TokenDto;
import ru.mai.delivery.dto.UserInfoDto;
import ru.mai.delivery.model.UserAccount;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public interface UserAccountService {

    UserAccount searchUsersById(Long id);

    Page<UserInfoDto> searchUsersByMask(String firstName, String lastName, PageRequest pageRequest);

    void register(RegisterDto registerDto);

    TokenDto authenticate(UserDetails userDetails);

    void updateUserInfo(UserInfoDto userInfoDto, UserDetails userDetails);

    void updateUserInfoById(Long userInfo, UserInfoDto userInfoDto);

    void deleteUserByUsername(String username);

}
