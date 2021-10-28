package com.agilemonkeys.crmservice.util;

import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.security.entity.Role;
import com.agilemonkeys.crmservice.security.enums.RoleName;
import com.agilemonkeys.crmservice.security.service.RoleService;
import com.agilemonkeys.crmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateRoles implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role roleAdmin = Role.builder().roleName(RoleName.ROLE_ADMIN).build();
        Role roleUser = Role.builder().roleName(RoleName.ROLE_USER).build();

        roleService.save(roleAdmin);
        roleService.save(roleUser);

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN));
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER));

        User user = User.builder().userName("admin").password("$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.").roles(roles).build();

        userService.save(user);
    }
}
