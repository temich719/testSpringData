package com.example.testspringdata.mapper;

import com.example.testspringdata.dto.RoleUserDTO;
import com.example.testspringdata.model.unik.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public RoleUserDTO mapFromUser(User user) {
        return new RoleUserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }



}
