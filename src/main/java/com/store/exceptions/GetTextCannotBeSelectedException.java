package com.store.exceptions;

public class GetTextCannotBeSelectedException extends RuntimeException{
	
	private String message;
	
	public GetTextCannotBeSelectedException(String message) {
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}

}
