package com.devplant.introduction.configuration;

import com.devplant.introduction.properties.SimpleProperties;
import com.devplant.introduction.service.GoodbyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(SimpleProperties.class)
public class SimpleConfiguration {

	@Autowired
	private SimpleProperties simpleProperties;

	@Bean
	public GoodbyeService goodbyeService() {
		return new GoodbyeService(simpleProperties.getGoodbyeMessage());
	}

	@Bean
	@Profile("dev")
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:4200");
			}
		};

	}

}
