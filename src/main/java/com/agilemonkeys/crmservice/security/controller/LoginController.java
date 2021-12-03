package com.agilemonkeys.crmservice.security.controller;

import com.agilemonkeys.crmservice.dto.UserDto;
import com.agilemonkeys.crmservice.security.dto.JwtDto;
import com.agilemonkeys.crmservice.security.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public JwtDto login(@Valid @RequestBody UserDto userLogin) {
        logger.info("Inside login of LoginController");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userLogin.getUserName(), userLogin.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return JwtDto.builder().token(jwt)
                .userName(userDetails.getUsername())
                .authorities(userDetails.getAuthorities())
                .build();
    }
}
