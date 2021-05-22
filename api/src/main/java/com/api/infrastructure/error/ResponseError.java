package com.api.infrastructure.error;

public class ResponseError {
	
	private String idMessage;
	private String message;
	
	public String getIdMessage() {
		return idMessage;
	}
	
	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
