package com.example.iPharmacy.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@PostMapping("/signup")
	public void signUp(@RequestBody String info) {
		JSONObject obj = new JSONObject(info);
		System.out.println("First Name: " + obj.getString("firstname"));
		System.out.println("Last Name: " + obj.getString("lastname"));
		System.out.println("Username: " + obj.getString("username"));
		System.out.println("Password: " + obj.getString("password"));
		System.out.println("Email: " + obj.getString("email"));

	}

}
