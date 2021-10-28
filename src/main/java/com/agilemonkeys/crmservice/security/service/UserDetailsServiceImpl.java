package com.agilemonkeys.crmservice.security.service;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.security.entity.UserPrincipal;
import com.agilemonkeys.crmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName);
        return UserPrincipal.build(user);
    }
}
