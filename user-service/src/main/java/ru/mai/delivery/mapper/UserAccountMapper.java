package ru.mai.delivery.mapper;

import org.mapstruct.Mapper;
import ru.mai.delivery.dto.LoginDto;
import ru.mai.delivery.dto.RegisterDto;
import ru.mai.delivery.model.UserAccount;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccount registerDtoToUserAccount(RegisterDto registerDto);

    LoginDto userAccountToLoginDto(UserAccount userAccount);

}
