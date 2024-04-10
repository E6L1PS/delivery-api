package ru.mai.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Создан: 10.04.2024.
 *
 * @author Pesternikov Danil
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "location")
public class Location {

//    @Id
//    private String id;

    private String currentLocation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime receiptDateTime;

}
