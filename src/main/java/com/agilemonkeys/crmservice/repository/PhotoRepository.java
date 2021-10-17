package com.agilemonkeys.crmservice.repository;

import com.agilemonkeys.crmservice.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
