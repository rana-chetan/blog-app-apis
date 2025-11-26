package com.example.blog.service;

import com.example.blog.DTOs.UserDto;
import com.example.blog.model.User;

public interface UserService {

    public UserDto createUser(UserDto user);

    public UserDto updateUser(UserDto user, Integer userId);

    public UserDto getUserById(Integer userId);

    public java.util.List<UserDto> getAllUsers();

    public void deleteUser(Integer userId);

}
