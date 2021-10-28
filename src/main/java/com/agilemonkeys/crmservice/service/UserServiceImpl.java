package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.repository.UserRepository;
import com.agilemonkeys.crmservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        if (StringUtils.hasText(user.getUserName())) {
            userDB.setUserName(user.getUserName());
        }

        if (StringUtils.hasText(user.getPassword())) {
            userDB.setPassword(user.getPassword());
        }

        return userRepository.save(userDB);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public User getByUserName(String userName) throws NotFoundException {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public User changeAdminStatus(Long userId, User user) throws NotFoundException {
        User userDB = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Constants.INVALID_USER_ID));

        if (Objects.nonNull(user.getRoles())) {
            userDB.setRoles(user.getRoles());
        }

        return userRepository.save(userDB);
    }
}