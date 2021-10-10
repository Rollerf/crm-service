package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void deleteUserById(Long userId);

    User updateUser(Long userId, User user);

    List<User> getUsersList();
}
