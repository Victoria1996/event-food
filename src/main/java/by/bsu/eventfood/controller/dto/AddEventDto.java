package by.bsu.eventfood.controller.dto;

import by.bsu.eventfood.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddEventDto {
    private String status;
    private Long placeId;
    private Integer numberOfAllPlaces;
    private Double rating;
    private String name;
    private String description;
    private String imagePath;
    private Double price;

    private String alternativeAddress;
    @JsonIgnore
    private Client client;

    /**
     * Start date
     */
    private Date date;
    private int hoursStart;
    private int minuteStart;
}
