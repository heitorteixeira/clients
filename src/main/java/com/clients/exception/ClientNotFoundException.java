package com.clients.exception;

public class ClientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientNotFoundException() {
		super();
	}
	
	public ClientNotFoundException(String msg) {
		super(msg);
	}
	
	public ClientNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
