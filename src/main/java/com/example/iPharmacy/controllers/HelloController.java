package com.example.iPharmacy.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

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
	public String printInput() {
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

	@GetMapping("/jsontest")
	public String jsonTest() {
		return "{\n" +
				"\t\"title\": \"HTN Dosage List\",\n" +
				"\t\"row\": 30,\n" +
				"\t\"col\": [\"Generic\", \"Brand\", \"Dose (mg)\", \"Max Dose(mg)\"],\n" +
				"\t\"questions\": [{\n" +
				"\t\t\t\"Generic\": \"Chlorthalidone\",\n" +
				"\t\t\t\"Brand\": [\"Thalitone\"],\n" +
				"\t\t\t\"Dose (mg)\": [\"25 QD\"],\n" +
				"\t\t\t\"Max Dose(mg)\": [\"100 QD\"]\n" +
				"\t\t},\n" +
				"\t\t{\n" +
				"\t\t\t\"Generic\": \"Lisinopril\",\n" +
				"\t\t\t\"Brand\": [\"Prinivil\", \"Zestril\", \"Qbrelis\"],\n" +
				"\t\t\t\"Dose (mg)\": [\"10-40 QD\"],\n" +
				"\t\t\t\"Max Dose(mg)\": [\"40 QD\"]\n" +
				"\t\t},\n" +
				"\t\t{\n" +
				"\t\t\t\"Generic\": \"Hydralazine\",\n" +
				"\t\t\t\"Brand\": [\"-1\"],\n" +
				"\t\t\t\"Dose (mg)\": [\"10-50 TID-QID\"],\n" +
				"\t\t\t\"Max Dose(mg)\": [\"200 QD\"]\n" +
				"\t\t}\n" +
				"\t]\n" +
				"}";
	}

	@GetMapping("/links")
	public String getLinks() throws IOException {
		Document doc = Jsoup.connect("http://anyurl.com").userAgent("Google").get();
		String linkList = "";
		try {

			// need http protocol
			doc = Jsoup.connect("http://google.com").get();

			// get page title
			String title = doc.title();
			System.out.println("title : " + title);

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				linkList += "\nlink : " + link.attr("href") + "\ntext : " + link.text();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return linkList;
	}
	@GetMapping("/team-member-4")
	public String teammate() {
		return "Ana Barcenas ";
	}

	public String correct = "Thalitone";
	public boolean checkAnswer(String answer) {
		String n_correct = correct.toLowerCase().replaceAll("\\s+","");
		String n_answer = answer.toLowerCase().replaceAll("\\s+","");
		return n_answer.equals(n_correct);
	}
}