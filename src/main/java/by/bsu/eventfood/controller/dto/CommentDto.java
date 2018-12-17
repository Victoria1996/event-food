package by.bsu.eventfood.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String comment;
    private Long id;
    private Long relatedToId;
    private Boolean isCurrentUser = false;
    private String userName;
    @JsonIgnore
    private Long clientId;
}
