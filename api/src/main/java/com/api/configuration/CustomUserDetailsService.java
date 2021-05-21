package com.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.domain.customer.Customer;
import com.api.domain.customer.CustomerJPARepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    private final CustomerJPARepository customerJPARepository;

    @Autowired
    public CustomUserDetailsService(CustomerJPARepository customerJPARepository) {
        this.customerJPARepository = customerJPARepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerJPARepository.getCustomerByUsername(username);
        if(customer == null) {
            throw new UsernameNotFoundException("Username " + username + " Not Found");
        }
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getEncryptedPassword(), new ArrayList<>());
    }
}
