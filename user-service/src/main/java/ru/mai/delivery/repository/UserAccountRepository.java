package ru.mai.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mai.delivery.model.UserAccount;

import java.util.Optional;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);

    boolean existsUserAccountByUsername(String username);

}
