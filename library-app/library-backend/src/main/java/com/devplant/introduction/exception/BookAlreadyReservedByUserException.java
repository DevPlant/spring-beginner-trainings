package com.devplant.introduction.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "stackTrace", "cause", "suppressed", "localizedMessage" })
public class BookAlreadyReservedByUserException extends RuntimeException {

	public BookAlreadyReservedByUserException() {
		super("You have already reserved this book");
	}
}
