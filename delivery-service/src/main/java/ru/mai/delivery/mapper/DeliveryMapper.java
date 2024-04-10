package ru.mai.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mai.delivery.dto.DeliveryDto;
import ru.mai.delivery.model.Delivery;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
@Mapper(componentModel = "spring")
public interface DeliveryMapper {


    @Mapping(target = "deliveryStatus", constant = "IN_TRANSIT")
    @Mapping(target = "locations", ignore = true)
    @Mapping(target = "locations", ignore = true)
    Delivery deliveryDtoToDelivery(DeliveryDto deliveryDto);

}
