package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.repository.CustomerRepository;
import com.agilemonkeys.crmservice.util.Constants;
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
    PhotoService photoService;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomerById(Long customerId, Customer customer) throws NotFoundException {
        Customer customerDB = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(Constants.INVALID_CUSTOMER_ID));

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

        if (Objects.nonNull(customer.getPhotoUrl())) {
            customerDB.setPhotoUrl(customer.getPhotoUrl());
        }

        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustomerById(Long customerId) throws NotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(Constants.INVALID_CUSTOMER_ID));
    }

    @Override
    public void deleteCustomerById(Long customerId) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
            photoService.deletePhoto(customer.get());
        } else {
            throw new NotFoundException(Constants.INVALID_CUSTOMER_ID);
        }
    }
}
