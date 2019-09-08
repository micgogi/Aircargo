package com.cargo.exception;
public class CustomerAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -2547317032607976157L;

	public CustomerAlreadyExistsException(String message) {
		super(message);
	}

}
