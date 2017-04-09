package com.devplant.introduction.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String sayHello(String who) {
		return "Hello, " + who;
	}

}
