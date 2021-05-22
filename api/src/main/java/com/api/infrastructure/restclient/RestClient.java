package com.api.infrastructure.restclient;

import com.api.domain.customer.Customer;

public interface RestClient {

    public Customer getCustomerData(Long customerId);
}
