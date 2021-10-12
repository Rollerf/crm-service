package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.dto.CustomerDto;
import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/customers")
    public CustomerDto saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        logger.info("Inside saveCustomer of customerController");
        Customer customer = dtoToCustomer(customerDto);
        Customer customerSaved = customerService.saveCustomer(customer);

        return customerToDto(customerSaved);
    }

    @GetMapping("/customers")
    public List<CustomerDto> getCustomersList() {
        logger.info("Inside getUsersList of customerController");

        return customerService.getCustomersList()
                .stream()
                .map(this::customerToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/customers")
    public CustomerDto updateCustomer(@PathVariable("id") Long customerId, @Valid @RequestBody CustomerDto customerDto) {
        logger.info("Inside updateCustomer of customerController");
        Customer customer = dtoToCustomer(customerDto);
        Customer customerSaved = customerService.updateCustomer(customerId, customer);

        return customerToDto(customerSaved);
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Long customerId) {
        logger.info("Inside getCustomer of customerController");
        Customer customer = customerService.getCustomerById(customerId);

        return customerToDto(customer);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomerById(@PathVariable("id") Long customerId) {
        logger.info("Inside deleteCustomerById of customerController");
        customerService.deleteCustomerById(customerId);

        return "Customer deleted successfully";
    }

    private Customer dtoToCustomer(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    private CustomerDto customerToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }
}