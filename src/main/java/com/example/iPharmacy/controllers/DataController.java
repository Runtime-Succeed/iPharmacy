package com.example.iPharmacy.controllers;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.QuestionSetRepository;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;

/**
 * Main controller for retrieving data
 */
@RestController()
@RequestMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
	
	private QuestionSetRepository qsRepo;
	private UserInfoRepository userRepo;
	
	@Autowired
	public DataController(QuestionSetRepository qsRepo, UserInfoRepository userRepo) {
		this.qsRepo = qsRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping("/htn-dosage-list")
	public QuestionSet getHtn() {
		return qsRepo.findById("HTN Dosage List").get();
	}
	
	@GetMapping("/titles")
	public String getTitles() {
		System.out.println("/data/titles reached");
		List<QuestionSet> qs = qsRepo.findAll();
		List<String> titles = new LinkedList<>();
		for(QuestionSet qset : qs)
			titles.add(qset.getTitle());
		
		return new JSONObject().put("titles", new JSONArray(titles)).toString();	//org.json, not org.json.simple		
	}
	
	@GetMapping("/users")
	public List<UserInfo> getUsers() {
		return userRepo.findAll();
	}
	
}
