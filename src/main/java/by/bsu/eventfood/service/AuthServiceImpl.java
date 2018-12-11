package by.bsu.eventfood.service;

import by.bsu.eventfood.controller.dto.SignUpRequestDto;
import by.bsu.eventfood.model.Client;
import by.bsu.eventfood.repository.ClientRepository;
import by.bsu.eventfood.service.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    @Transactional
    public Client signUp(SignUpRequestDto dto) {
        Client client = null;

        if (!clientRepository.existsByEmail(dto.getEmail())) {
            client = clientRepository.save(clientMapper.map(dto));

            log.info("User {} was registered", dto.getLogin());
        }

        return client;
    }

    @Override
    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
