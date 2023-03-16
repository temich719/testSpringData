package com.example.testspringdata.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDTO {
    private long id;
    private String email;
    private String password;
    private String role;
}
