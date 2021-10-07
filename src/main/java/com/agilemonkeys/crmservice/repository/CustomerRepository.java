package com.agilemonkeys.crmservice.repository;

import com.agilemonkeys.crmservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
