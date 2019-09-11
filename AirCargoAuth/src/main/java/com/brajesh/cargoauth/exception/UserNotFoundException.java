package com.brajesh.cargoauth.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{
	private String message;

	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserNotFoundException [message=" + message + "]";
	}
	
}
