package ru.mai.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.delivery.model.Authority;
import ru.mai.delivery.repository.UserAccountRepository;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Service
@RequiredArgsConstructor
public class UserAccountDetailService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username)
                .map(userAccount -> User.builder()
                        .username(userAccount.getUsername())
                        .password(userAccount.getPassword())
                        .authorities(
                                userAccount.getAuthorities().stream()
                                        .map(Authority::getAuthority)
                                        .map(SimpleGrantedAuthority::new)
                                        .toList())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }

}
