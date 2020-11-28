package com.clients.exception;

public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CityNotFoundException() {
		super();
	}
	
	public CityNotFoundException(String msg) {
		super(msg);
	}
	
	public CityNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
