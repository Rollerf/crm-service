package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.repository.PhotoRepository;
import com.agilemonkeys.crmservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    CustomerService customerService;

    @Autowired
    PhotoRepository photoRepository;

    public String uploadPhoto(MultipartFile multipartFile, Long customerId, Long userId) throws NotFoundException {
        String photoUrl;

        try {
            photoUrl = photoRepository.uploadPhoto(multipartFile, customerId);
            Customer customer = Customer.builder().customerId(customerId).photoUrl(photoUrl).build();

            customerService.updateCustomerById(customerId, customer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException(Constants.INVALID_PHOTO);
        }

        return photoUrl;
    }

    public String deletePhoto(Customer customer, Long userId) {
        if (customer == null || customer.getPhotoUrl() == null) {
            throw new NotFoundException(Constants.INVALID_USER_ID);
        }

        String photoUrl = customer.getPhotoUrl();
        Customer customerSaved = Customer.builder().customerId(customer.getCustomerId()).photoUrl("").build();

        photoRepository.deletePhoto(photoUrl);
        customerService.updateCustomerById(customer.getCustomerId(), customerSaved);

        return "Photo deleted successfully";
    }

    public String deletePhoto(Customer customer) {
        if (customer == null || customer.getPhotoUrl() == null) {
            throw new NotFoundException(Constants.INVALID_USER_ID);
        }

        String photoUrl = customer.getPhotoUrl();

        photoRepository.deletePhoto(photoUrl);

        return "Photo deleted successfully";
    }
}