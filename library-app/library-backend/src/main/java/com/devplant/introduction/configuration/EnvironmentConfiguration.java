package com.devplant.introduction.configuration;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EnableWebMvc
public class EnvironmentConfiguration {


	@Autowired
	private Environment environment;

	@PostConstruct
	protected void postConstruct(){

		log.info(" ---> Running with profiles: " + Lists.newArrayList(environment.getActiveProfiles()));

	}

	@Bean
	@Profile("dev")
	public WebMvcConfigurer devCorsConfigurer() {

		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:4200")
						.allowedMethods(HttpMethod.GET.name(), HttpMethod.DELETE.name(), HttpMethod.POST.name(),
								HttpMethod.PUT.name(), HttpMethod.OPTIONS.name());
			}
		};

	}

}
