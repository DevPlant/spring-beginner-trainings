package com.devplant.introduction.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "stackTrace", "cause", "suppressed", "localizedMessage" })
public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(){
		super("User Already Exists");
	}

}
