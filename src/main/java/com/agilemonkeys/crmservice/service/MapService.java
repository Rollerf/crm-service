package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.dto.CustomerDto;
import com.agilemonkeys.crmservice.dto.UserDto;
import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.entity.User;

public interface MapService {
    Customer dtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToDto(Customer customer);

    User dtoToUser(UserDto userDto);

    UserDto userToDto(User user);
}
