package com.devplant.introduction.rest.user;

import com.devplant.introduction.exception.UserAlreadyExistsException;
import com.devplant.introduction.rest.user.model.UserRegistrationModel;
import com.devplant.introduction.security.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-management")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody UserRegistrationModel userRegistrationModel) {
		userService.register(userRegistrationModel);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { UserAlreadyExistsException.class })
	protected String handleUserAlreadyExistsException(UserAlreadyExistsException exception) throws JsonProcessingException {
		return objectMapper.writeValueAsString(exception);
	}

}
