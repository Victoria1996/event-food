package by.bsu.eventfood.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    private String status;
    private String url;
}
