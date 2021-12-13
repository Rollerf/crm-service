package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    String uploadPhoto(MultipartFile multipartFile, Long customerId, Long userId) throws NotFoundException;

    String deletePhoto(Customer customer, Long userId);

    String deletePhoto(Customer customer);
}
