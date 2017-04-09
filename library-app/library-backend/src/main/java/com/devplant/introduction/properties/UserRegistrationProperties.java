package com.devplant.introduction.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("user.registration")
public class UserRegistrationProperties {

	private String activationSuccessUrl;

	private String activationFailedUrl;

	private String accountAlreadyActivatedUrl;

	private String activationLinkTemplate;
}
