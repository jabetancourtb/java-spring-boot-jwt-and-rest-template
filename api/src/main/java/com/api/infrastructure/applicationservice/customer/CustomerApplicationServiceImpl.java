package com.api.infrastructure.applicationservice.customer;

import com.api.domain.customer.Customer;
import com.api.domain.customer.CustomerJPARepository;
import com.api.infrastructure.dto.customer.CustomerDTO;
import com.api.infrastructure.dto.signin.SignInDTO;
import com.api.infrastructure.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final CustomerJPARepository customerJPARepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomerApplicationServiceImpl(CustomerJPARepository customerJPARepository
        , AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.customerJPARepository = customerJPARepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    private String encryptPassword(String plainPassword) {
        return ENCODER.encode(plainPassword);
    }

    @Override
    public Customer signUp(CustomerDTO customerDTO) {
    	try {
    		String encryptedPassword = encryptPassword(customerDTO.getPlainPassword());
	        Customer customer = new Customer(customerDTO.getFirstName(), customerDTO.getLastName()
	        , customerDTO.getUsername(), encryptedPassword);
	        return customerJPARepository.save(customer);
    	}
    	catch(Exception e) {
    		throw e;
    	}
    }

    @Override
    public String signIn(SignInDTO signInDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInDTO.getUsername(),
                    signInDTO.getPlainPassword()
            ));
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials", e);
        }

        final Customer customer = customerJPARepository.getCustomerByUsername(signInDTO.getUsername());

        return jwtUtil.generateToken(customer);
    }
}
