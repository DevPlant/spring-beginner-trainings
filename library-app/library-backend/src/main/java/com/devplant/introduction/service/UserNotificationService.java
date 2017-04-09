package com.devplant.introduction.service;

import com.devplant.introduction.properties.UserRegistrationProperties;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class UserNotificationService {

	@Value("${spring.mail.username:admin@devplant.ro")
	private String severEmailAddress;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRegistrationProperties userRegistrationProperties;

	public void sendUserActivationEmail(String userEmail, String firstName, String lastName, String activationId)
			throws UnsupportedEncodingException {
		final Email email = DefaultEmail.builder().from(new InternetAddress(severEmailAddress, "Devplant"))
				.to(Lists.newArrayList(new InternetAddress(userEmail, firstName + " " + lastName)))
				.subject("Activate your DevPlant Library Account").body("Click <a href=\"" + String
						.format(userRegistrationProperties.getActivationLinkTemplate(), activationId)
																		+ "\">here<a/> to activate your account")
				.encoding("UTF-8").build();

		emailService.send(email);
	}
}
