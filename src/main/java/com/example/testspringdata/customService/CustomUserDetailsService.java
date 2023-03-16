package com.example.testspringdata.customService;

import com.example.testspringdata.dao.UserService;
import com.example.testspringdata.dto.RoleUserDTO;
import com.example.testspringdata.model.unik.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findFirstUserByEmail(username);
        RoleUserDTO roleUser = new RoleUserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
        Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(roleUser.getRole()));
        return new org.springframework.security.core.userdetails.User(roleUser.getEmail(), roleUser.getPassword(), authorities);
    }

}
