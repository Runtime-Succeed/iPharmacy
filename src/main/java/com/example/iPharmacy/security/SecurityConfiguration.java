package com.example.iPharmacy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private NoOpPasswordEncoder bCrypt;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/page.html").permitAll()
			.antMatchers("/login**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login.html")
			.and()
			.logout()
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.and().csrf().disable();
		//super.configure(http);
	}
	
	@Bean
	public CustomUserDetailsService createCustomUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public NoOpPasswordEncoder getEncoder() {
	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	//USE AUTHPROVIDER!!!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(createCustomUserDetailsService()).passwordEncoder(bCrypt);
	}
	
	 @Bean
	   @Override
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	   }


}
