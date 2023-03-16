package com.example.testspringdata.dao;

import com.example.testspringdata.model.unik.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends JpaRepository<User, Long> {

    User findFirstUserByEmail(String email);

}
