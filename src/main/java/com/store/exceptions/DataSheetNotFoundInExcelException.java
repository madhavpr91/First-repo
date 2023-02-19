package com.store.exceptions;

public class DataSheetNotFoundInExcelException extends RuntimeException{
	private String message;
	
	public DataSheetNotFoundInExcelException(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	

}
