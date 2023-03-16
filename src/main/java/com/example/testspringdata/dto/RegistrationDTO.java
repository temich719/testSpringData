package com.example.testspringdata.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String registrationTime;
    private double temperature;
}
