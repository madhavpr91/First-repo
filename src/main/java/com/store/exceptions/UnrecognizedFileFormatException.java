package com.store.exceptions;

public class UnrecognizedFileFormatException extends RuntimeException{
	private String message;
	
	public UnrecognizedFileFormatException(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	

}
