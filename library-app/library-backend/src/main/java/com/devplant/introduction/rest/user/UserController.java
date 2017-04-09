package com.devplant.introduction.rest.user;

import com.devplant.introduction.domain.User;
import com.devplant.introduction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user-management")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/self")
	public ResponseEntity<User> self(Principal principal){
		if(principal==null){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			return new ResponseEntity<>(userRepository.findOneByUsername(principal.getName()),HttpStatus.OK);
		}
	}
}
