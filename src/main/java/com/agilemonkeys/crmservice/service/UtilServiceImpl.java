package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.security.entity.Role;
import com.agilemonkeys.crmservice.security.enums.RoleName;
import com.agilemonkeys.crmservice.security.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RoleService roleService;

    @Override
    public String getImageUrl(Long customerId) {
        StringBuilder photoUrl = new StringBuilder();
        String url = environment.getProperty("spring.url");
        photoUrl.append(url);
        photoUrl.append("/photos/");
        photoUrl.append(customerId);

        return photoUrl.toString();
    }

    public Set<Role> getRoles(Set<String> rolesNames) {
        Set<Role> roles = new HashSet<>();

        roles.add(roleService.getByRoleName(RoleName.ROLE_USER));

        if (rolesNames.contains("admin")) {
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN));
        }

        return roles;
    }
}
