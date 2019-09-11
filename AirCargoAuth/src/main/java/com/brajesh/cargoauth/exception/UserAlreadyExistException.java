package com.brajesh.cargoauth.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception{
	
	private String message;
	
	public UserAlreadyExistException() {
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistException(String message) {
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
		return "UserAlreadyExistException [message=" + message + "]";
	}

}

