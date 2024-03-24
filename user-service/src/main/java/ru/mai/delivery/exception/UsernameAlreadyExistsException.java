package ru.mai.delivery.exception;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
