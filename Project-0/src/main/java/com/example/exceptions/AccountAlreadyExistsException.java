package com.example.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountAlreadyExistsException() {
		super("This users bank account already exists.");
		}

}
