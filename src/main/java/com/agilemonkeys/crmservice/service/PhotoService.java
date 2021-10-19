package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Photo;
import com.agilemonkeys.crmservice.error.NotFoundException;

public interface PhotoService {
    Photo savePhoto(Photo photo);

    void deletePhotoById(Long customerId) throws NotFoundException;

    Photo updatePhotoById(Long customerId, Photo photo) throws NotFoundException;

    Photo getPhotoById(Long customerId) throws NotFoundException;

    boolean isPhotoExist(Long customerId);
}
