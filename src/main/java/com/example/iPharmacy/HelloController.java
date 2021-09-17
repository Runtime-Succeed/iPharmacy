package com.example.iPharmacy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class HelloController {
	
	/**
	 * Try these links:
	 * http://localhost:8080
	 * http://localhost:8080/random-number
	 * http://localhost:8080/display-accounts
	 * http://localhost:8080/index.html
	 */
	
	List<Account> accounts = new ArrayList<>();

	@GetMapping("/")
	public String index() {
		return "Hello World! Yuan-Chieh Ying says hi.";
	}

}