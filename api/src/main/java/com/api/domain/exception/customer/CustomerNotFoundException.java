package com.api.domain.exception.customer;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {
	private static final String MESSAGE = "Customer Not found";
	
	public CustomerNotFoundException(final RuntimeException exception) {
		super(MESSAGE, exception);
	}
}
