package by.bsu.eventfood.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
