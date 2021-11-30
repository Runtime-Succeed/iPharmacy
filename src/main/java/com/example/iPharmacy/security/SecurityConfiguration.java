package com.example.iPharmacy.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/login*").permitAll()
			.antMatchers("/signup*").permitAll()
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/index.html").permitAll()
			.antMatchers("/groupintro.html").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login.html")
			.and()
				.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			.and().csrf().disable();
	}

}
