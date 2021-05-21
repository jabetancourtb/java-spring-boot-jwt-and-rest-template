package com.api.infrastructure.rest.client;

import com.api.infrastructure.dto.customer.CustomerDTO;

public interface RestClient {

    public CustomerDTO getCustomerData(Long customerId);
}
