package com.devplant.introduction.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "stackTrace", "cause", "suppressed", "localizedMessage" })
public class BookPickupDateIsToFarInTheFutureException extends RuntimeException {

	public BookPickupDateIsToFarInTheFutureException(){
		super("Pickup date is to far in the future");
	}
}
