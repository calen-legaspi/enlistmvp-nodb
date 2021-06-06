package com.orangeandbronze.enlistment;

public class EnlistmentException extends RuntimeException {

	EnlistmentException() {
	}

	EnlistmentException(String message) {
		super(message);
	}

	EnlistmentException(Throwable cause) {
		super(cause);
	}

	EnlistmentException(String message, Throwable cause) {
		super(message, cause);
	}

	EnlistmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
