package com.devplant.introduction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class SpringSecurityDemonstrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemonstrationApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll().and().logout()
					.permitAll().and().csrf().disable();
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
					.password("admin").roles("ADMIN").and().withUser("nasty").password("nasty").roles("USER");
		}
	}
}
