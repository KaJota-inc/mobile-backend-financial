package com.mobilebackendfinancial.financial.exception;

public class ValidationException extends RuntimeException{
 
	private static final long serialVersionUID = 2379489762457488406L;

	public ValidationException(String message){
        super(message);
    }
}
