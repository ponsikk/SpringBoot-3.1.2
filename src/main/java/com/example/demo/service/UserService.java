package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    void updateUser(Long id, User user);
}