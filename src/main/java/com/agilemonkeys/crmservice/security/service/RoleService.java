package com.agilemonkeys.crmservice.security.service;

import com.agilemonkeys.crmservice.security.entity.Role;
import com.agilemonkeys.crmservice.security.enums.RoleName;

public interface RoleService {
    Role getByRoleName(RoleName roleName);

    Role save(Role role);
}
