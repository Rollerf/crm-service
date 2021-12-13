package com.agilemonkeys.crmservice.security.service;

import com.agilemonkeys.crmservice.security.entity.Role;
import com.agilemonkeys.crmservice.security.enums.RoleName;

import java.util.Set;

public interface RoleService {
    Role getByRoleName(RoleName roleName);

    Role save(Role role);

    Set<Role> getRoles(Set<String> rolesNames);
}
