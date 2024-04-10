package ru.mai.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mai.delivery.dto.ParcelDto;
import ru.mai.delivery.model.Parcel;

/**
 * Создан: 22.03.2024.
 *
 * @author Pesternikov Danil
 */
@Mapper(componentModel = "spring")
public interface ParcelMapper {

    @Mapping(target = "creationDate", expression = "java(java.time.LocalDate.now())")
    Parcel parcelDtoToParcel(ParcelDto parcelDto);

}
