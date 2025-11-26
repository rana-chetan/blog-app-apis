package com.example.blog.DTOs;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotBlank
    @Size(min = 4, message = "Username must be minimum of 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotNull
    @Size(min = 3, max = 10, message = "Password must be between 3 to 10 characters")
    private String password;

    @NotNull
    private String about;

}
