package com.devplant.introduction.service;

public class GoodbyeService {

	private String message;

	public GoodbyeService(String message) {
		this.message = message;
	}

	public String sayGoodbye() {
		return message;
	}
}
