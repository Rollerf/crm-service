package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.security.entity.Role;
import java.util.Set;

public interface UtilService {
    String getImageUrl(Long customerId);

    Set<Role> getRoles(Set<String> rolesNames);
}
