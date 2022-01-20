package com.agilemonkeys.crmservice.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoRepository {
    String uploadPhoto(MultipartFile multipartFile, Long customerId) throws IOException;
    void deletePhoto(String urlPhoto);
}
