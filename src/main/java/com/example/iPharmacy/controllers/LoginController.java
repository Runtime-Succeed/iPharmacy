package com.example.iPharmacy.controllers;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class LoginController {
	
	private UserInfoRepository userRepo;
	private static ObjectMapper mapper;
	
	@Autowired
	public LoginController(UserInfoRepository userRepo, ObjectMapper mapper) {
		this.userRepo = userRepo;
		LoginController.mapper = mapper;
	}
	
	@PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode signUp(@RequestBody UserInfo newUser) {
		System.out.println("signup reached");
		ObjectNode response = mapper.createObjectNode();
		if (!userRepo.doesUsernameExist(newUser.getUsername())) {
			userRepo.insert(newUser);
			return response.put("success", true);
		}
		return response.put("success", false);
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void login(@RequestBody ObjectNode body) throws JsonProcessingException {
		
		String inputUsername = body.get("username").asText();
		String storedUser = userRepo.findPasswordAndSaltByUsername(inputUsername);
		
		//if a user with the specified username exists
		if(storedUser != null) {
			
			String inputPassword = body.get("password").asText();
			JsonNode obj = mapper.readTree(storedUser);
			String storedSalt = obj.get("salt").asText();
			String storedPassword = obj.get("password").asText();
			
			//hash user-entered password with same salt and compare result with stored password
			String hashedInputPassword = Hex.encodeHexString(
					UserInfo.hashPassword(inputPassword.toCharArray(), storedSalt.getBytes())
					);
			
			if(hashedInputPassword.equals(storedPassword))
				System.out.println("Login Succeeded.");
			else
				System.out.println("Login Failed.");
		}
		else
			System.out.println("Login Failed.");
		
	}

}
