package com.store.exceptions;

public class ValueInListBoxNotFoundException extends RuntimeException {
	private String message;
	public ValueInListBoxNotFoundException(String message) {
		this.message = message;
	}

	public ValueInListBoxNotFoundException() {
		this.message = "Value in the listbox was not found.";
	}
	
	public String getMessage() {
		return message;
	}

}
