package com.api.infrastructure.applicationservice.customer;

import com.api.domain.customer.Customer;
import com.api.infrastructure.dto.customer.CustomerDTO;
import com.api.infrastructure.dto.signin.SignInDTO;

public interface CustomerApplicationService {

    Customer signUp(CustomerDTO customerDTO);

    String signIn(SignInDTO signInDTO);
}
