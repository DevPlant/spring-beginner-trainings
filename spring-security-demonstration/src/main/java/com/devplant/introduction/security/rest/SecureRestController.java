package com.devplant.introduction.security.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("secure")
public class SecureRestController {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	private boolean allowRemoteShutdown = false;

	private String welcomeText = "Welcome";

	@RequestMapping("get-welcome")
	public String getWelcome() {
		return welcomeText;
	}

	@RequestMapping(value = "change-welcome", method = RequestMethod.POST, consumes = "text/plain")
	public void noXssInput(@RequestBody String welcomeText) {
		this.welcomeText = welcomeText;
	}

	@RequestMapping(value = "/stupid", method = RequestMethod.GET)
	public String allowRemoteShutdown(HttpServletRequest httpServletRequest) {
		this.allowRemoteShutdown = true;
		log.info("Stupid method called from : " + httpServletRequest.getHeader("Referer"));
		return "I'm A Stupid get Method that modifies data!";
	}

	@RequestMapping(value = "/shutdown-app", method = RequestMethod.POST)
	public void shutdownApp(@RequestParam("param") String param) {
		if (param.equalsIgnoreCase("SHUTDOWN")) {
			if (allowRemoteShutdown) {
				applicationContext.close();
			}
		}
	}

}
