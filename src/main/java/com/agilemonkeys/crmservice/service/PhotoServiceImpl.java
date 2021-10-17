package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Photo;
import com.agilemonkeys.crmservice.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhotoById(Long customerId) {
        photoRepository.deleteById(customerId);
    }

    @Override
    public Photo updatePhotoById(Long customerId, Photo photo) {
        photoRepository.deleteById(customerId);

        return photoRepository.save(photo);
    }

    @Override
    public Photo getPhotoById(Long customerId) {
        Optional<Photo> photo = photoRepository.findById(customerId);
        return photo.get();
    }

    @Override
    public boolean isPhotoExist(Long customerId) {
        return photoRepository.existsById(customerId);
    }
}
