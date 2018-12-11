package by.bsu.eventfood.service.mapper;

import by.bsu.eventfood.controller.dto.SignUpRequestDto;
import by.bsu.eventfood.model.Client;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper(uses = DataMapper.class)
@Component
public abstract class ClientMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "role", source = "roleName")
    })
    public abstract Client map(SignUpRequestDto dto);

    @AfterMapping
    public void afterMapping(@MappingTarget Client client) {
        String password = client.getPassword();
        if (Strings.isNotEmpty(password)) {
            client.setPassword(passwordEncoder.encode(password));
        }

        Date currentDate = new Date();
        client.setCreated(currentDate);
        client.setModified(currentDate);
        client.setRegistrationDate(currentDate);
    }
}
