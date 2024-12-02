package com.example.demo.util;

import com.example.demo.model.User;
import com.example.demo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;




@Component
public class UserValidator implements Validator {

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public UserValidator(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            customUserDetailService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException u) {
            return;
        }

        errors.rejectValue("username", "Пользователь с таким именем уже существует!");
    }
}