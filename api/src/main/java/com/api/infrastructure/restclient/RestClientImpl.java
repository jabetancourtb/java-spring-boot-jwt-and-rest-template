package com.api.infrastructure.restclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.domain.customer.Customer;
import com.api.domain.exception.customer.CustomerNotFoundException;

@Service
public class RestClientImpl implements RestClient {

	private static final String MOCKAROO_API_URL = "https://my.api.mockaroo.com/customer/";
	private static final String MOCKAROO_API_KEY = "5946a1a0";
	private static final String X_API_KEY = "X-API-Key";
	
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Customer getCustomerData(Long customerId) {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set(X_API_KEY, MOCKAROO_API_KEY);
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(headers); 
    	
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(MOCKAROO_API_URL)
    			.path(customerId.toString());
    	
        ResponseEntity<Customer> customerDataResponse = restTemplate.exchange(builder.toUriString()
        		, HttpMethod.GET, httpEntity, Customer.class);
        
        if(customerDataResponse.getBody() == null) {
        	throw new CustomerNotFoundException(null);
        }
        
        return customerDataResponse.getBody();
    }
}
