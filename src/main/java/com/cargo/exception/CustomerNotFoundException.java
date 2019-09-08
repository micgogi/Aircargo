package com.cargo.exception;
public class CustomerNotFoundException extends Exception {


	private static final long serialVersionUID = 5529833866229857888L;

	public CustomerNotFoundException(String message) {
		super(message);
	}

}
