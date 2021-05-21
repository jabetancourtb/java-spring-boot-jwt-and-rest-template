package com.api.infrastructure.rest.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.infrastructure.dto.customer.CustomerDTO;

@Service
public class RestClientImpl implements RestClient {

	private static final String MOOCKARO_API_URL = "https://my.api.mockaroo.com/customer/";
	private static final String MOOCKARO_API_KEY = "5946a1a0";
	private static final String X_API_KEY = "X-API-Key";
	
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public CustomerDTO getCustomerData(Long customerId) {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set(X_API_KEY, MOOCKARO_API_KEY);
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(headers); 
    	
        ResponseEntity<String> customerDataResponse = restTemplate.exchange(MOOCKARO_API_URL + customerId + ".json"
        		, HttpMethod.GET, httpEntity, String.class);
        
        String firstName = customerDataResponse.getBody().split(",")[1];
        String lastName = customerDataResponse.getBody().split(",")[2];
        String userName = customerDataResponse.getBody().split(",")[3];
        
        CustomerDTO customerDTO = new CustomerDTO(firstName, lastName, userName, null);
        
        return customerDTO;
    }
}
