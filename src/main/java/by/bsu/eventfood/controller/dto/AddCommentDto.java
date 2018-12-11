package by.bsu.eventfood.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDto {
    private String text;
    private Long placeId;
    private Long relatedToId;
}
