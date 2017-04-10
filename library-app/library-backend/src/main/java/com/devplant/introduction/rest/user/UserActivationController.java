package com.devplant.introduction.rest.user;

import com.devplant.introduction.domain.User;
import com.devplant.introduction.properties.UserRegistrationProperties;
import com.devplant.introduction.repository.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/api/user-management")
public class UserActivationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRegistrationProperties userRegistrationProperties;

	@RequestMapping("/activate")
	public ModelAndView activate(@RequestParam("activationId") String activationId) {

		User user = userRepository.findOneByActivationId(activationId);
		log.info("Found user : "+user+ " -> "+activationId);
		RedirectView redirectView;
		if (user != null) {

			if (!user.isEnabled()) {
				user.setEnabled(true);
				userRepository.save(user);
				redirectView = new RedirectView(userRegistrationProperties.getActivationSuccessUrl());
			} else {
				redirectView = new RedirectView(userRegistrationProperties.getAccountAlreadyActivatedUrl());
			}
		} else {
			redirectView = new RedirectView(userRegistrationProperties.getActivationFailedUrl());
		}
		redirectView.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
		return new ModelAndView(redirectView);
	}

}
