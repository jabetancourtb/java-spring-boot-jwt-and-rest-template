package com.api.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT id, first_name, last_name, username, encrypted_password"
    		+ " FROM Customer WHERE username = :username", nativeQuery = true)
    Customer getCustomerByUsername(@Param("username") String username);
}
