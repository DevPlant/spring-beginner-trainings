package com.devplant.introduction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.devplant.introduction.configuration.Roles.ROLE_ADMIN;
import static com.devplant.introduction.configuration.Roles.ROLE_USER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin().disable()
				.authorizeRequests().antMatchers("/api/user-management/**").permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.GET.toString()))
				.hasAnyRole(ROLE_ADMIN, ROLE_USER)
				.requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.OPTIONS.toString()))
				.hasAnyRole(ROLE_ADMIN, ROLE_USER).antMatchers("/api/**").hasRole(ROLE_ADMIN)
				.antMatchers("/api/user/**").hasRole(ROLE_ADMIN).and().httpBasic();
	}

}
