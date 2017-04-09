package com.devplant.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Tweet {

	public static void main(String[] args) {
		SpringApplication.run(Tweet.class, args);
	}

	@RequestMapping
	public String hello(){
		return "Hello from Spring!";
	}

}
