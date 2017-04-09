package com.devplant.introduction.security;

import com.devplant.introduction.domain.User;
import com.devplant.introduction.exception.UserAlreadyExistsException;
import com.devplant.introduction.repository.UserRepository;
import com.devplant.introduction.rest.user.model.UserRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User existingUser = userRepository.findOneByUsername(username);
		if (existingUser == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return existingUser;
		}
	}

	public void register(UserRegistrationModel userRegistrationModel) {
		User existingUser = userRepository.findOneByUsername(userRegistrationModel.getEmail());
		if (existingUser == null) {
			existingUser = new User(userRegistrationModel.getFirstName(), userRegistrationModel.getLastName(),
					userRegistrationModel.getEmail(), passwordEncoder.encode(userRegistrationModel.getPassword()));
			userRepository.save(existingUser);
		} else {
			throw new UserAlreadyExistsException();
		}
	}

}
