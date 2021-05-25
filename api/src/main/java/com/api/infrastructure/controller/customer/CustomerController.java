package com.api.infrastructure.controller.customer;

import com.api.domain.customer.Customer;
import com.api.infrastructure.applicationservice.customer.CustomerApplicationService;
import com.api.infrastructure.dto.customer.CustomerDTO;
import com.api.infrastructure.dto.jwt.JwtDTO;
import com.api.infrastructure.dto.signin.SignInDTO;

import com.api.infrastructure.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;
    private final RestClient restClient;

    @Autowired
    public CustomerController( CustomerApplicationService customerApplicationService
    , RestClient restClient) {
        this.customerApplicationService = customerApplicationService;
        this.restClient = restClient;
    }

    @PostMapping
    public ResponseEntity<Customer> signUp(@RequestBody CustomerDTO customerDTO) {
        Customer customer = null;
        try {
            customer = customerApplicationService.signUp(customerDTO);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtDTO> signIn(@RequestBody SignInDTO signInDTO) {
        String token = null;
        try {
            token = customerApplicationService.signIn(signInDTO);
            return new ResponseEntity<>(new JwtDTO(token), HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new JwtDTO(token), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer>getCustomerFromRestClient(@PathVariable("id") final long customerId) {
        Customer customer = null;
        try {
            customer = restClient.getCustomerData(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }
    }

}