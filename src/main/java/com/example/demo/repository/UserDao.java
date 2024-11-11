package com.example.demo.repository;

import com.example.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long id);

}
