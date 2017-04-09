package com.devplant.introduction.rest.user;

import com.devplant.introduction.rest.user.model.UserRegistrationModel;
import com.devplant.introduction.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-management")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody UserRegistrationModel userRegistrationModel) {
		userService.register(userRegistrationModel);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
