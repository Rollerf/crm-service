package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.error.DuplicateIdException;
import com.agilemonkeys.crmservice.error.NotFoundException;

import java.util.List;

public interface UserService {
    User save(User user) throws DuplicateIdException;

    void deleteUserById(Long userId) throws NotFoundException;

    User updateUserById(Long userId, User user) throws NotFoundException, DuplicateIdException;

    List<User> getUsersList();

    User getByUserName(String userName) throws NotFoundException;

    boolean existByUserName(String userName);

    User changeAdminStatus(Long userId, User user) throws NotFoundException;
}
