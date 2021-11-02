package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.dto.CustomerDto;
import com.agilemonkeys.crmservice.dto.NewUserDto;
import com.agilemonkeys.crmservice.dto.UserDto;
import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.entity.User;
import com.agilemonkeys.crmservice.security.entity.Role;
import java.util.Set;

public interface UtilService {
    Customer dtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToDto(Customer customer);

    String getImageUrl(Long customerId);

    Set<Role> getRoles(Set<String> rolesNames);

    User dtoToUser(UserDto userDto);

    User dtoToUser(NewUserDto userDto);

    UserDto userToDto(User user);
}
