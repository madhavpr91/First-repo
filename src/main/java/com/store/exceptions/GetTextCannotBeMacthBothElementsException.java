package com.store.exceptions;

public class GetTextCannotBeMacthBothElementsException extends RuntimeException{
	private String message;
	
	public GetTextCannotBeMacthBothElementsException(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}

}
