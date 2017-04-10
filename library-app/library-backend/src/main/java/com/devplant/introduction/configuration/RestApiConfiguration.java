package com.devplant.introduction.configuration;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RestApiConfiguration extends RepositoryRestConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath("api");
		if (Lists.newArrayList(environment.getActiveProfiles()).contains("dev")) {
			config.getCorsRegistry().addMapping("/api/**").allowedOrigins("http://localhost:4200");
		}
	}

}