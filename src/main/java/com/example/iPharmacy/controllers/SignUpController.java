package com.example.iPharmacy.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.database.CustomUserInfoRepository;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class SignUpController {
	
	@Autowired
	private UserInfoRepository userRepo;
	
	@Autowired 
	private CustomUserInfoRepository customUserRepo;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode signUp(@RequestBody ObjectNode newUser) throws UnsupportedEncodingException {
		
		ObjectNode response = mapper.createObjectNode();
		
		if (!customUserRepo.doesUsernameExist(newUser.get("username").asText())) {
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

}
