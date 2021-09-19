package com.example.iPharmacy;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		return "Hello World!";
	}

	@RequestMapping(value = "/random-number", method = RequestMethod.GET)
	public String randomNumber()
	{
		return "Your random number is: " + ((int)(Math.random() * 100) + 1);
	}

	@RequestMapping(value = "/display-accounts", method = RequestMethod.GET)
	public String displayAccounts()
	{
		return new Gson().toJson(accounts);
	}
	
	@RequestMapping(value = "/display-accounts-pretty", method = RequestMethod.GET, produces = "text/plain")
	public String displayAccountsPretty()
	{
		return new GsonBuilder().setPrettyPrinting().create().toJson(accounts);
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST)
	public Account createAccount(
			@RequestParam("username") String username,
			@RequestParam("password") String password
	) {
		Account newAccount = new Account(username, password);
		accounts.add(newAccount);
		return newAccount;
	}

	@GetMapping("/myName")
	public String jayName() {
		return "Yuan-Chieh Ying";
	}

	@GetMapping("/CXue")
	public String name_Chen() {
		return "The World say hello to Chen";
	}

	@GetMapping("/siwen")
	public String name_wang() {
		return "Hello Siwen";
	}
}