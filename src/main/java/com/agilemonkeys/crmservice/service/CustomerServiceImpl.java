package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.repository.CustomerRepository;
import com.agilemonkeys.crmservice.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomerById(Long customerId, Customer customer) {
        Customer customerDB = customerRepository.findById(customerId).get();

        if (StringUtils.hasText(customer.getName())) {
            customerDB.setName(customer.getName());
        }

        if (StringUtils.hasText(customer.getSurname())) {
            customerDB.setSurname(customer.getSurname());
        }

        if (Objects.nonNull(customer.getCreatedBy())) {
            customerDB.setCreatedBy(customer.getCreatedBy());
        }

        if (Objects.nonNull(customer.getUpdatedBy())) {
            customerDB.setUpdatedBy(customer.getUpdatedBy());
        }

        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        return customer.get();
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        photoRepository.deleteById(customerId);
        customerRepository.deleteById(customerId);
    }
}
