package com.devplant.introduction.configuration;

import com.devplant.introduction.security.UserService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.devplant.introduction.configuration.Roles.ROLE_ADMIN;
import static com.devplant.introduction.configuration.Roles.ROLE_USER;

@Slf4j
@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override

	public void configure(HttpSecurity http) throws Exception {

		if (Lists.newArrayList(environment.getActiveProfiles()).contains("dev")) {
			http.csrf().disable();
		} else {
			http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		}

		http.formLogin().disable().authorizeRequests().antMatchers("/api/user-management/**").permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/users/**")).hasRole(ROLE_ADMIN)
				.requestMatchers(new AntPathRequestMatcher("/api/logout", HttpMethod.POST.toString()))
				.hasRole(ROLE_USER)
				.requestMatchers(new AntPathRequestMatcher("/api/books/reservation", HttpMethod.POST.toString()))
				.hasRole(ROLE_USER).requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.DELETE.toString()))
				.hasRole(ROLE_ADMIN).requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.POST.toString()))
				.hasRole(ROLE_ADMIN).requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.PUT.toString()))
				.hasRole(ROLE_ADMIN).requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.GET.toString()))
				.permitAll().requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.OPTIONS.toString()))
				.permitAll().and().httpBasic().authenticationEntryPoint((request, response, authException) -> {
			HttpServletResponse httpResponse = response;
			httpResponse.addHeader("WWW-Authenticate", "Application driven");
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		});
	}

	@Bean
	public DaoAuthenticationProvider jpaDaoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@RestController
	public static class LogoutController {

		@RequestMapping(value = "/api/logout", method = RequestMethod.POST)
		public ResponseEntity<Void> logout(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
				SecurityContextHolder.clearContext();
			}

			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
