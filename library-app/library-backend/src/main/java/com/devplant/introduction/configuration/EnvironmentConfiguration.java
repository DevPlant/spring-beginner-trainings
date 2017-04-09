package com.devplant.introduction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class EnvironmentConfiguration {

	@Bean
	@Profile("dev")
	public WebMvcConfigurer devCorsConfigurer() {

		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:4200");
			}
		};

	}

	@Bean
	@Profile("dev")
	public WebSecurityConfigurerAdapter devWebSecurityConfigurerAdapter() {
		return new WebSecurityConfigurerAdapter() {

			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.csrf().disable();
			}
		};
	}

	@Bean
	@Profile("prod")
	public WebSecurityConfigurerAdapter prodWebSecurityConfigurerAdapter() {
		return new WebSecurityConfigurerAdapter() {

			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			}
		};
	}
}
