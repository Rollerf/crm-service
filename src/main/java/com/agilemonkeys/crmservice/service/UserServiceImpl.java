package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.repository.UserRepository;
import com.agilemonkeys.crmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User userDB = userRepository.findById(userId).get();

        if(Objects.nonNull(userDB.getAdmin())){
            userDB.setAdmin(user.getAdmin());
        }

        return userRepository.save(userDB);
    }

    @Override
    public List<User> getLstUsers() {
        return userRepository.findAll();
    }
}