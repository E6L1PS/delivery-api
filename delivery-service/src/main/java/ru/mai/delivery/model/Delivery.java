package ru.mai.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "delivery")
public class Delivery {

    @Id
    private String id;

    private Long senderId;

    private Long recipientId;

    //    @DBRef
    private List<Location> locations;

    private DeliveryStatus deliveryStatus;

}
