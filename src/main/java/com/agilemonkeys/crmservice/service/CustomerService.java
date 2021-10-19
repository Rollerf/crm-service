package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getCustomersList();

    Customer updateCustomerById(Long customerId, Customer customer) throws NotFoundException;

    Customer getCustomerById(Long customerId) throws NotFoundException;

    void deleteCustomerById(Long customerId) throws NotFoundException;
}
