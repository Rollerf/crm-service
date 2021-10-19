package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.repository.UserRepository;
import com.agilemonkeys.crmservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) throws NotFoundException {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new NotFoundException(Constants.INVALID_USER_ID);
        }
    }

    @Override
    public User updateUserById(Long userId, User user) throws NotFoundException {
        User userDB = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Constants.INVALID_USER_ID));

        if (Objects.nonNull(userDB.getAdmin())) {
            userDB.setAdmin(user.getAdmin());
        }

        return userRepository.save(userDB);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}