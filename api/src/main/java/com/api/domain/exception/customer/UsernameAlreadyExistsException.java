package com.api.domain.exception.customer;

@SuppressWarnings("serial")
public class UsernameAlreadyExistsException extends RuntimeException {
	private static final String MESSAGE = "Username already exists";
		
	public UsernameAlreadyExistsException(final RuntimeException exception) {
		super(MESSAGE, exception);
	}
}
