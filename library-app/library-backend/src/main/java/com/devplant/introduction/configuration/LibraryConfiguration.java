package com.devplant.introduction.configuration;

import com.devplant.introduction.properties.UserRegistrationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserRegistrationProperties.class)
public class LibraryConfiguration {
}
