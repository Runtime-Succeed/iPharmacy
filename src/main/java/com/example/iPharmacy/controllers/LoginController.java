package com.example.iPharmacy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class LoginController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private AuthenticationProvider authProvider;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode login(@RequestBody ObjectNode body) {
		
		String username = body.get("username").asText();
		String password = body.get("password").asText();
		
		try {
			Authentication auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(auth);
			return mapper.createObjectNode().put("loginSuccess", true).put("username", username);
		}
		catch(BadCredentialsException e) {
			return mapper.createObjectNode().put("loginSuccess", false);
		}		
	}

}
