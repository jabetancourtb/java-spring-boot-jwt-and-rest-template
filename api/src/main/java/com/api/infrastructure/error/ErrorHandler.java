package com.api.infrastructure.error;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.api.domain.exception.customer.CustomerNotFoundException;
import com.api.domain.exception.customer.UsernameAlreadyExistsException;

public class ErrorHandler {
  
	//This is not the best way to handle exceptions
	
	public ResponseEntity<Object> handleException(
		  Exception ex) {
		ResponseError response = new ResponseError();
		HttpStatus status = null;
		
		if(ex instanceof DataIntegrityViolationException) {
			response.setIdMessage("1");
			response.setMessage("Verify that the data are valid");   
			status = HttpStatus.BAD_REQUEST;
		} 
		else if(ex instanceof BadCredentialsException) {
			response.setIdMessage("2");
			response.setMessage(ex.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} 
		else if(ex instanceof UsernameAlreadyExistsException) {
			response.setIdMessage("3");
			response.setMessage(ex.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		else if (ex instanceof CustomerNotFoundException) {
			response.setIdMessage("4");
			response.setMessage(ex.getMessage());
			status = HttpStatus.NOT_FOUND;
		}
		else if (ex instanceof NoSuchElementException) {
			response.setIdMessage("5");
			response.setMessage(ex.getMessage());
			status = HttpStatus.NOT_FOUND;
		}
		else if (ex instanceof MethodArgumentTypeMismatchException) {
			response.setIdMessage("6");
			response.setMessage(ex.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			response.setIdMessage("7");
			response.setMessage("Something went wrong");  
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(response, status);
   }
}