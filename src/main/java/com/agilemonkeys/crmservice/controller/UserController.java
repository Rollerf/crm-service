package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) {
        logger.info("Inside saveUser of userController");
        return userService.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsersList() {
        logger.info("Inside getUsersList of userController");
        return userService.getUsersList();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) {
        logger.info("Inside deleteUserById of userController");
        userService.deleteUserById(userId);
        return "User deleted successfully";
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Long userId, @Valid @RequestBody User user){
        logger.info("Inside updateUser of userController");
        return userService.updateUser(userId, user);
    }
}
