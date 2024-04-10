package ru.mai.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Создан: 09.04.2024.
 *
 * @author Pesternikov Danil
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parcel")
public class Parcel {

    @Id
    private String id;

    private String description;

    private String weight;

    private String dimensions;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

}
