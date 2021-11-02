package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.dto.NewUserDto;
import com.agilemonkeys.crmservice.dto.UserDto;
import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.security.entity.Role;
import com.agilemonkeys.crmservice.service.UserService;
import com.agilemonkeys.crmservice.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UtilService utilService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public UserDto newUser(@Valid @RequestBody NewUserDto userDto) {
        logger.info("Inside saveUser of userController");

        User user = utilService.dtoToUser(userDto);
        Set<Role> roles = utilService.getRoles(userDto.getRolesName());

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User userSaved = userService.save(user);

        return utilService.userToDto(userSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<UserDto> getUsersList() {
        logger.info("Inside getUsersList of userController");

        return userService.getUsersList()
                .stream()
                .map(userDto->utilService.userToDto(userDto))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) throws NotFoundException {
        logger.info("Inside deleteUserById of userController");
        userService.deleteUserById(userId);

        return "User deleted successfully";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto) throws NotFoundException {
        logger.info("Inside updateUser of userController");
        User user = utilService.dtoToUser(userDto);

        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User userSaved = userService.updateUserById(userId, user);

        return utilService.userToDto(userSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/{id}")
    public UserDto changeRoles(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto) throws NotFoundException {
        logger.info("Inside changeAdminStatus of userController");
        User user = utilService.dtoToUser(userDto);
        Set<Role> roles = utilService.getRoles(userDto.getRolesName());

        user.setRoles(roles);

        User userSaved = userService.changeAdminStatus(userId, user);

        return utilService.userToDto(userSaved);
    }
}
