package ru.mai.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mai.delivery.model.Authority;

import java.util.Optional;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Optional<Authority> findByAuthority(String authority);

}
