package com.orangeandbronze.enlistment;

public class SectionsException extends RuntimeException {

	public SectionsException() {
	}

	public SectionsException(String message) {
		super(message);
	}

	public SectionsException(Throwable cause) {
		super(cause);
	}

	public SectionsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SectionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
