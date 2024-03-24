package ru.mai.delivery.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.delivery.dto.UserInfoDto;
import ru.mai.delivery.model.UserAccount;

import java.util.Optional;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM UserAccount u WHERE u.username = :username")
    void deleteByUsername(@NonNull @Param("username") String username);

    @Transactional
    @Modifying
    @Query("""
            UPDATE UserAccount u
            SET u.firstName = COALESCE(:firstName, u.firstName),
            u.lastName = COALESCE(:lastName, u.lastName),
            u.number = COALESCE(:number, u.number),
            u.email = COALESCE(:email, u.email)
            WHERE u.username = :username
            """)
    void updateFirstNameAndLastNameAndNumberAndEmailByUsername(
            @NotNull @Param("username") String username,
            @Nullable @Param("firstName") String firstName,
            @Nullable @Param("lastName") String lastName,
            @Nullable @Param("number") String number,
            @Nullable @Param("email") String email
    );

    Page<UserInfoDto> findByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageable);

    Optional<UserAccount> findByUsername(String username);

    boolean existsUserAccountByUsername(String username);

}
