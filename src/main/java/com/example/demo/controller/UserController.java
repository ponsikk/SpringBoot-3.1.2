package com.example.demo.controller;


import com.example.demo.service.UserService;
import com.example.demo.dto.UserDto;
import com.example.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/users")
    public class UserController {

        @Autowired
        private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        return "user-list";
    }


    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("userDto") UserDto userDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "user-list";
        }
        userService.addUser(new User(userDto.getName(), userDto.getLastName(), userDto.getEmail()));
        return "redirect:/users";

    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email) {
        userService.getUserById(id).ifPresent(user -> {
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.updateUser(user);
    });
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

