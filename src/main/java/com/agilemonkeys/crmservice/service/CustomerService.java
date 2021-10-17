package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getCustomersList();

    Customer updateCustomerById(Long customerId, Customer customer);

    Customer getCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);
}
