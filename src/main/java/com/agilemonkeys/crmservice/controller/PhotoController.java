package com.agilemonkeys.crmservice.controller;

import com.agilemonkeys.crmservice.entity.Customer;
import com.agilemonkeys.crmservice.entity.Photo;
import com.agilemonkeys.crmservice.service.CustomerService;
import com.agilemonkeys.crmservice.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @PostMapping("/photos/{id}")
    public String uploadFile(@PathVariable("id") Long customerId, @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Inside savePhoto of photoController");

        Customer customer = customerService.getCustomerById(customerId);
        Photo photo = mapToPhoto(file, customer);

        photo.setCustomer(customer);

        photoService.savePhoto(photo);

        return "Photo added successfully";
    }

    @GetMapping(value = "/photos/{id}")
    public void getPhoto(@PathVariable("id") Long customerId, HttpServletResponse response) throws IOException {
        logger.info("Inside getPhoto of photoController");
        Photo photo = photoService.getPhotoById(customerId);

        try (OutputStream os = response.getOutputStream()) {
            os.write(photo.getContentBytes());
        } catch (IOException e) {
            throw new IOException(e);
        }

        response.setContentType(photo.getContentType());
        response.setContentLengthLong(photo.getSize());
    }

    @PutMapping("/photos/{id}")
    public String updatePhoto(@PathVariable("id") Long customerId, @Valid @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Inside updatePhoto of photoController");

        Customer customer = customerService.getCustomerById(customerId);
        Photo photo = mapToPhoto(file, customer);

        photoService.updatePhotoById(customerId, photo);

        return "Photo updated successfully";
    }

    @DeleteMapping("/photos/{id}")
    public String deletePhotoById(@PathVariable("id") Long customerId) {
        logger.info("Inside deletePhotoById of photoController");

        photoService.deletePhotoById(customerId);

        return "Photo deleted successfully";
    }

    private Photo mapToPhoto(MultipartFile multipartFile, Customer customer) throws IOException {
        Photo photo = new Photo();

        photo.setContentBytes(multipartFile.getBytes());
        photo.setContentType(multipartFile.getContentType());
        photo.setSize(multipartFile.getSize());
        photo.setCustomer(customer);

        return photo;
    }
}
