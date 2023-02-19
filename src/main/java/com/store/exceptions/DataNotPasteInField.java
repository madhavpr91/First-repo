package com.store.exceptions;

public class DataNotPasteInField extends RuntimeException{
	
	private String message;
	
	public DataNotPasteInField(String message) {
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}

}
