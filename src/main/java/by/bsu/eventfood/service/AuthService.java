package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.SignUpRequestDto;
import by.bsu.eventfood.model.Client;

public interface AuthService {
    Client signUp(SignUpRequestDto signUpRequestDto);

    Client getByEmail(String email);
}
