package com.store.exceptions;

public class InvalidBrowserException extends RuntimeException{
	private String message;
	
	public InvalidBrowserException(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	

	
	
}
