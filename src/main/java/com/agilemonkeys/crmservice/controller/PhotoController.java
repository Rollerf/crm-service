package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.security.entity.UserPrincipal;
import com.agilemonkeys.crmservice.service.CustomerService;
import com.agilemonkeys.crmservice.service.PhotoService;
import com.agilemonkeys.crmservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    CustomerService customerService;

    private final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @PostMapping("/photos/{id}")
    public ResponseEntity<Object> uploadFile(@PathVariable("id") Long customerId, @RequestParam("file") MultipartFile multipartFile, Authentication authentication) throws NotFoundException {
        logger.info("Inside savePhoto of photoController");

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        if (multipartFile.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INVALID_PHOTO);
        }

        return ResponseEntity.ok(photoService.uploadPhoto(multipartFile, customerId, userPrincipal.getUserId()));
    }

    @PutMapping("/photos/{id}")
    public ResponseEntity<String> updatePhoto(@PathVariable("id") Long customerId, @RequestParam("file") MultipartFile multipartFile, Authentication authentication) throws NotFoundException {
        logger.info("Inside updatePhoto of photoController");

        if (multipartFile.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INVALID_PHOTO);
        }

        Customer customer = customerService.getCustomerById(customerId);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        photoService.deletePhoto(customer, userPrincipal.getUserId());

        return ResponseEntity.ok(photoService.uploadPhoto(multipartFile, customerId, userPrincipal.getUserId()));
    }

    @DeleteMapping("/photos/{id}")
    public String deletePhotoById(@PathVariable("id") Long customerId, Authentication authentication){
        logger.info("Inside deletePhotoById of photoController");

        Customer customer = customerService.getCustomerById(customerId);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return photoService.deletePhoto(customer, userPrincipal.getUserId());
    }
}
