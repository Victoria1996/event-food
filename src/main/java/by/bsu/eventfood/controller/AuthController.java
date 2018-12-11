package by.bsu.eventfood.controller;

import by.bsu.eventfood.controller.dto.ResponseDto;
import by.bsu.eventfood.controller.dto.SignInRequestDto;
import by.bsu.eventfood.controller.dto.SignUpRequestDto;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.security.CustomUserDetails;
import by.bsu.eventfood.service.AuthService;
import by.bsu.eventfood.service.RoleActionUrlResolver;
import by.bsu.eventfood.service.RoleActionUrlResolver.ActionUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.function.Supplier;

import static by.bsu.eventfood.service.RoleActionUrlResolver.ActionUrl.SIGN_IN;
import static by.bsu.eventfood.service.RoleActionUrlResolver.ActionUrl.SIGN_UP;

@RestController
@Slf4j
public class AuthController {
    private final static String SUCCESS_AUTH_STATUS = "success";

    @Autowired
    private AuthService authService;

    @Autowired
    private CompositeLogoutHandler logoutHandler;

    @Autowired
    private RoleActionUrlResolver urlResolver;

    @PostMapping("/sign-up")
    public ResponseDto signUpWithAutoLogin(@Valid @RequestBody SignUpRequestDto dto, HttpServletRequest request) {
        final Client client = authService.signUp(dto);

        if (client == null) {
            return ResponseDto.builder()
                    .status("Client with login " + dto.getEmail() + " already exists")
                    .build();
        }

        ResponseDto responseDto = login(request, dto.getEmail(), dto.getPassword());

        return enrichAuthDtoWithURL(SIGN_UP, () -> client, responseDto);
    }

    @PostMapping("/sign-in")
    public ResponseDto signIn(@Valid @RequestBody SignInRequestDto dto, HttpServletRequest request) {
        return enrichAuthDtoWithURL(SIGN_IN,
                () -> authService.getByEmail(dto.getLogin()),
                login(request, dto.getLogin(), dto.getPassword()));
    }

    @PostMapping("/sign-out")
    public ResponseDto signOut(HttpServletRequest request, HttpServletResponse response,
                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        log.info("User {} was logged out", userDetails.getUsername());

        return ResponseDto.builder()
                .status(SUCCESS_AUTH_STATUS)
                .url("/home").build();
    }

    private ResponseDto login(HttpServletRequest request, String login, String password) {
        String status = SUCCESS_AUTH_STATUS;
        try {
            request.login(login, password);

            log.info("User with {} was logged in", login);
        } catch (ServletException e) {
            log.error("Failed to proceed login {}", e.getMessage());

            status = e.getMessage();
        }

        return ResponseDto.builder()
                .status(status)
                .build();
    }

    private ResponseDto enrichAuthDtoWithURL(ActionUrl action, Supplier<Client> supplier, ResponseDto responseDto) {
        if (SUCCESS_AUTH_STATUS.equals(responseDto.getStatus())) {
            responseDto.setUrl(urlResolver.resolve(action, supplier.get()));
        }

        return responseDto;
    }
}
