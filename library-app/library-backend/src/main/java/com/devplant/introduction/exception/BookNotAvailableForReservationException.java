package com.devplant.introduction.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "stackTrace", "cause", "suppressed", "localizedMessage" })
public class BookNotAvailableForReservationException extends RuntimeException {

	public BookNotAvailableForReservationException(){
		super("Book is not available for reservation");
	}
}
