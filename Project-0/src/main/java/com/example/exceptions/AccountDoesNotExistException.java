package com.example.exceptions;

public class AccountDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistException() {
		super("No bank account attatched to this user");	}

}
