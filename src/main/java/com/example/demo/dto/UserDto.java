package com.example.demo.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "Имя обязательно")
    private String name;

    @NotEmpty(message = "Фамилия обязательна")
    private String lastName;

    @NotEmpty(message = "Email обязателен")
    @Email(message = "Некорректный формат Email")
    private String email;
}
