package by.bsu.eventfood.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPlaceDto {
    private String name;
    private int fullNumberOfPlaces;
    private double rating;
    private String address;
    private String description;
    private List<WorkingHourDto> workingHours;
    private List<TableTypeDto> typesOfTables;
}
