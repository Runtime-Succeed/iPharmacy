package com.example.iPharmacy.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class LoginController {
	
	private UserInfoRepository userRepo;
	private static ObjectMapper mapper;
	
	@Autowired
	private AuthenticationProvider authProvider;
	
	@Autowired
	public LoginController(UserInfoRepository userRepo, ObjectMapper mapper) {
		this.userRepo = userRepo;
		LoginController.mapper = mapper;
	}
	
	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode signUp(@RequestBody ObjectNode newUser) throws UnsupportedEncodingException {
		System.out.println("signup reached");
		ObjectNode response = mapper.createObjectNode();
		
		if (!userRepo.doesUsernameExist(newUser.get("username").asText())) {
			userRepo.insert(new UserInfo(
					newUser.get("firstName").asText(),
					newUser.get("lastName").asText(),
					newUser.get("email").asText(),
					newUser.get("username").asText(),
					newUser.get("password").asText()
					));
			return response.put("signupSuccess", true);
		}
		return response.put("signupSuccess", false);
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode login(@RequestBody ObjectNode body) {
		
		String username = body.get("username").asText();
		String password = body.get("password").asText();
		
		System.out.println("username: " + username + "\npassword: " + password);

		Authentication auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return new ObjectMapper().createObjectNode().put("loginSuccess", true).put("username", username);
	}

}
