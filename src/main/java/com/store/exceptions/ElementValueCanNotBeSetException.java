package com.store.exceptions;

public class ElementValueCanNotBeSetException extends RuntimeException{
	private String message;
	
	public ElementValueCanNotBeSetException(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	

	
	
}
