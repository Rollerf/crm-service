package com.agilemonkeys.crmservice.security.service;

import com.agilemonkeys.crmservice.security.entity.Role;
import com.agilemonkeys.crmservice.security.enums.RoleName;
import com.agilemonkeys.crmservice.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Set<Role> getRoles(Set<String> rolesNames) {
        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER));

        if (rolesNames.contains("admin")) {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN));
        }

        return roles;
    }
}
