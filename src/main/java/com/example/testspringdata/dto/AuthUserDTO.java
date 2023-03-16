package com.example.testspringdata.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO {
    private String email;
    private String password;
}
