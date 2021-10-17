package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Photo;

public interface PhotoService {
    Photo savePhoto(Photo photo);

    void deletePhotoById(Long customerId);

    Photo updatePhotoById(Long customerId, Photo photo);

    Photo getPhotoById(Long customerId);

    boolean isPhotoExist(Long customerId);
}
