package ru.mai.delivery.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.delivery.config.JwtService;
import ru.mai.delivery.dto.RegisterDto;
import ru.mai.delivery.dto.TokenDto;
import ru.mai.delivery.dto.UserInfoDto;
import ru.mai.delivery.exception.UsernameAlreadyExistsException;
import ru.mai.delivery.mapper.UserAccountMapper;
import ru.mai.delivery.model.Authority;
import ru.mai.delivery.model.UserAccount;
import ru.mai.delivery.repository.AuthorityRepository;
import ru.mai.delivery.repository.UserAccountRepository;

import java.util.List;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final AuthorityRepository authorityRepository;

    private final JwtService jwtService;

    private final UserAccountMapper userAccountMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserInfoDto> searchUsersByMask(String firstName, String lastName, PageRequest pageRequest) {
        return userAccountRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName, pageRequest);
    }

    @Override
    public void register(RegisterDto registerDto) {
        if (userAccountRepository.existsUserAccountByUsername(registerDto.username())) {
            throw new UsernameAlreadyExistsException(
                    "Пользователь с именем: '" + registerDto.username() + "' уже существует!"
            );
        }
        Authority defaultAuthority = authorityRepository.findByAuthority("ROLE_USER")
                .orElseGet(() -> authorityRepository.save(Authority.builder().authority("ROLE_USER").build()));

        String encodedPassword = passwordEncoder.encode(registerDto.password());
        UserAccount userAccount = userAccountMapper.registerDtoToUserAccount(registerDto);
        userAccount.setPassword(encodedPassword);
        userAccount.setAuthorities(List.of(defaultAuthority));
        userAccountRepository.save(userAccount);
    }

    @Override
    public TokenDto authenticate(UserDetails userDetails) {
        return jwtService.generateToken(userDetails);
    }

    @Override
    public void updateUserInfo(UserInfoDto userInfoDto, UserDetails userDetails) {
        userAccountRepository.updateFirstNameAndLastNameAndNumberAndEmailByUser(
                userDetails,
                userInfoDto.firstName(),
                userInfoDto.lastName(),
                userInfoDto.number(),
                userInfoDto.email()
        );
    }

    @Override
    public void updateUserInfoById(Long id, UserInfoDto userInfoDto) {
        userAccountRepository.updateFirstNameAndLastNameAndNumberAndEmailByUserId(
                id,
                userInfoDto.firstName(),
                userInfoDto.lastName(),
                userInfoDto.number(),
                userInfoDto.email()
        );
    }

    @Override
    public void deleteUserByUsername(String username) {
        if (userAccountRepository.existsUserAccountByUsername(username)) {
            userAccountRepository.deleteByUsername(username);
        } else throw new UsernameNotFoundException(
                "Пользователь с именем: '" + username + "' не существует!"
        );
    }

}
