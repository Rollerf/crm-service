package com.agilemonkeys.crmservice.repository;

import com.agilemonkeys.crmservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    boolean existsByUserName(String userName);
}
