package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.error.NotFoundException;

import java.util.List;

public interface UserService {
    User save(User user);

    void deleteUserById(Long userId) throws NotFoundException;

    User updateUserById(Long userId, User user) throws NotFoundException;

    List<User> getUsersList();
}
