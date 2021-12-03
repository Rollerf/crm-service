package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.dto.CustomerDto;
import com.agilemonkeys.crmservice.dto.UserDto;
import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Customer dtoToCustomer(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    @Override
    public CustomerDto customerToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    public User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
