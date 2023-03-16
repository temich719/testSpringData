package com.example.testspringdata.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private long id;
    private String token;
    private String role;
}
