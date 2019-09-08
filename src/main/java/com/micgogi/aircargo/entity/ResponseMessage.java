package com.micgogi.aircargo.entity;

public class ResponseMessage {
	private String message;


	

	public ResponseMessage(String message) {
		this.message = message;
	}

	public ResponseMessage(){
		
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}

