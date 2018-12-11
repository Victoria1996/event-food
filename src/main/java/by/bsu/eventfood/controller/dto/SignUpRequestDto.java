package by.bsu.eventfood.controller.dto;

import by.bsu.eventfood.model.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotEmpty
    private String login;

    private String name;

    private String surnname;

    private String phonenumber;

    @NotEmpty
    private String email;

    private String sex;

    @NotEmpty
    private String password;

    @NotNull
    private RoleName roleName;
}
