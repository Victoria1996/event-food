package by.bsu.eventfood.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceReservationDto {
    private List<TableTypeDto> typesOfTables;
    private String placeName;
    @JsonIgnore
    private Long placeId;

    private String userName;
    private String userPhoneNumber;
}
