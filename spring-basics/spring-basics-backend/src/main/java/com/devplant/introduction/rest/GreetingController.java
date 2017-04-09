package com.devplant.introduction.rest;

import com.devplant.introduction.model.GreetingModel;
import com.devplant.introduction.service.GoodbyeService;
import com.devplant.introduction.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

	@Autowired
	private GoodbyeService goodbyeService;

	@Autowired
	private HelloService helloService;

	@RequestMapping("/hello")
	public GreetingModel sayHello(@RequestParam String who) {
		return new GreetingModel(helloService.sayHello(who));
	}

	@RequestMapping("/goodbye")
	public GreetingModel sayGoodbye() {
		return new GreetingModel(goodbyeService.sayGoodbye());
	}

}
