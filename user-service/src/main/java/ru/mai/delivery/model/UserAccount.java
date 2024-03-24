package ru.mai.delivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_account", schema = "auth_schema")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String number;

    private String email;

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_authority", schema = "auth_schema",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;

}
