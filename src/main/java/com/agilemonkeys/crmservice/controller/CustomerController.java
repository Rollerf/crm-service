package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.dto.CustomerDto;
import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.security.entity.UserPrincipal;
import com.agilemonkeys.crmservice.service.CustomerService;
import com.agilemonkeys.crmservice.service.MapService;
import com.agilemonkeys.crmservice.service.PhotoService;
import com.agilemonkeys.crmservice.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UtilService utilService;

    @Autowired
    private MapService mapService;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto, Authentication authentication) {
        logger.info("Inside saveCustomer of customerController");
        Customer customer = mapService.dtoToCustomer(customerDto);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        customer.setCreatedBy(userPrincipal.getUserId());

        Customer customerSaved = customerService.saveCustomer(customer);

        return mapService.customerToDto(customerSaved);
    }

    @GetMapping("/customers")
    public List<CustomerDto> getCustomersList() {
        logger.info("Inside getUsersList of customerController");

        return customerService.getCustomersList()
                .stream()
                .map (customerDto->mapService.customerToDto(customerDto))
                .collect(Collectors.toList());
    }

    @PutMapping("/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable("id") Long customerId,
                                      @Valid @RequestBody CustomerDto customerDto,
                                      Authentication authentication) throws NotFoundException {
        logger.info("Inside updateCustomer of customerController");
        Customer customer = mapService.dtoToCustomer(customerDto);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        customer.setUpdatedBy(userPrincipal.getUserId());

        Customer customerSaved = customerService.updateCustomerById(customerId, customer);

        return mapService.customerToDto(customerSaved);
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Long customerId) throws NotFoundException {
        logger.info("Inside getCustomer of customerController");
        Customer customer = customerService.getCustomerById(customerId);

        CustomerDto customerDto = mapService.customerToDto(customer);

        if (photoService.isPhotoExist(customerId)) {
            customerDto.setImageUrl(utilService.getImageUrl(customerId));
        }

        return customerDto;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomerById(@PathVariable("id") Long customerId) throws NotFoundException {
        logger.info("Inside deleteCustomerById of customerController");
        customerService.deleteCustomerById(customerId);

        return "Customer deleted successfully";
    }
}