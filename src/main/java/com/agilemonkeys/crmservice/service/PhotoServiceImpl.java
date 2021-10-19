package com.agilemonkeys.crmservice.service;

import com.agilemonkeys.crmservice.entity.Photo;
import com.agilemonkeys.crmservice.error.NotFoundException;
import com.agilemonkeys.crmservice.repository.PhotoRepository;
import com.agilemonkeys.crmservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhotoById(Long customerId) throws NotFoundException {
        try {
            photoRepository.deleteById(customerId);
        } catch (Exception e) {
            throw new NotFoundException(Constants.INVALID_PHOTO_ID);
        }
    }

    @Override
    public Photo updatePhotoById(Long customerId, Photo photo) throws NotFoundException {
        try {
            photoRepository.deleteById(customerId);
        } catch (Exception e) {
            throw new NotFoundException(Constants.INVALID_PHOTO_ID);
        }

        return photoRepository.save(photo);
    }

    @Override
    public Photo getPhotoById(Long customerId) throws NotFoundException {
        return photoRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(Constants.INVALID_PHOTO_ID));
    }

    @Override
    public boolean isPhotoExist(Long customerId) {
        return photoRepository.existsById(customerId);
    }
}
