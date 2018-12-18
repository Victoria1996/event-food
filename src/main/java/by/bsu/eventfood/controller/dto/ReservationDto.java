package by.bsu.eventfood.controller.dto;

import by.bsu.eventfood.model.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private ReservationType type;
    private Long id;
    private String userName;
    private String userPhoneNumber;
    private String comment;

    private Long idOfTable;
    /**
     * Start date
     */
    private Date date;
    private int hoursStart;
    private int minuteStart;
}
