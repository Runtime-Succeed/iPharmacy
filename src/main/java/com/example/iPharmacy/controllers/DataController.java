package com.example.iPharmacy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.QuestionSetRepository;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		return qsRepo.findFirstByTitle("HTN Dosage List");
	}
	
	@GetMapping("/titles")
	public String getTitles() throws JsonProcessingException {
		List<QuestionSet> qs = qsRepo.findAllTitles();
		ObjectMapper obj = new ObjectMapper().setSerializationInclusion(Include.NON_DEFAULT);
		return obj.writeValueAsString(qs);
	}
	
	@GetMapping("/users")
	public List<UserInfo> getUsers() {
		return userRepo.findAll();
	}
	
}
