package com.store.exceptions;

public class CheckBoxCannotBeSelectedException extends RuntimeException{
	
	private String message;
	
	public CheckBoxCannotBeSelectedException(String message) {
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}
	
	
	
}
