package com.devplant.introduction.service;

import com.devplant.introduction.properties.UserRegistrationProperties;
import com.devplant.introduction.rest.user.model.UserRegistrationModel;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserNotificationService {

	@Value("${spring.mail.username:admin@devplant.ro}")
	private String severEmailAddress;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRegistrationProperties userRegistrationProperties;

	public void sendUserActivationEmail(UserRegistrationModel userRegistrationModel, String activationId) {
		try {
			Email email = DefaultEmail.builder().from(new InternetAddress(severEmailAddress, "DevPlant")).to(Lists
					.newArrayList(new InternetAddress(userRegistrationModel.getEmail(),
							userRegistrationModel.getFirstName() + " " + userRegistrationModel.getLastName())))
					.subject("Activate your DevPlant Library Account").body("").encoding("UTF-8").build();

			final Map<String, Object> modelObject = new HashMap<>();
			modelObject.put("activationLink",
					String.format(userRegistrationProperties.getActivationLinkTemplate(), activationId));

			emailService.send(email, "activation-email.ftl", modelObject);
		} catch (UnsupportedEncodingException e) {
			log.error("Something is miss-configured, as this cannot happen", e);
		} catch (CannotSendEmailException e) {
			log.error("Cloud not send activation-email", e);
		}

	}

}
