package com.example.testspringdata.mapper;

import com.example.testspringdata.dto.RegistrationDTO;
import com.example.testspringdata.model.unik.Registration;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public RegistrationDTO mapFromModel(Registration registration) {
        return new RegistrationDTO(registration.getRegistrationTime(), registration.getTemperature());
    }

}
