package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.dto.UserDto;
import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Inside saveUser of userController");
        User user = dtoToUser(userDto);
        User userSaved = userService.save(user);

        return userToDto(userSaved);
    }

    @GetMapping("/users")
    public List<UserDto> getUsersList() {
        logger.info("Inside getUsersList of userController");

        return userService.getUsersList()
                .stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) {
        logger.info("Inside deleteUserById of userController");
        userService.deleteUserById(userId);

        return "User deleted successfully";
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto){
        logger.info("Inside updateUser of userController");
        User user = dtoToUser(userDto);
        User userSaved = userService.updateUser(userId, user);

        return userToDto(userSaved);
    }

    private User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
