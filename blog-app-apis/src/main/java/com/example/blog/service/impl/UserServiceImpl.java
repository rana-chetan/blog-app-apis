package com.example.blog.service.impl;

import com.example.blog.DTOs.UserDto;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        User user1 = this.DtoToUser(user);
        User save = userRepository.save(user1);

        return this.UserToDto(save);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {

        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());

        User updatedUser = userRepository.save(user1);
        return this.UserToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.UserToDto(user1);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = userRepository.findAll();

        List<UserDto> userDtoList = userList.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user1);
    }

    // Converting DTO to User Entity
    private User DtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    // Converting User Entity to DTO
    private UserDto UserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
