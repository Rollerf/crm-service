package com.agilemonkeys.crmservice.repository;

import com.agilemonkeys.crmservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Native Named Param
    @Query(
            value = "SELECT * FROM customers c WHERE c.customer_id = :customerId",
            nativeQuery = true
    )
    Customer getCustomerById(@Param("customerId") Long customerId);
}
