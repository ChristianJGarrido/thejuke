package com.netbuilder.thejuke.exceptions;


/**
 * @author Taylor Hunter
 * 
 * Exception for where an invalid ID is used in a service.
 */

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 8900477800383063914L;

	/**
	 * @param message Message paired with exception
	 */
	public ValidationException(String message) {
		super(message);
	}
	
	
}
