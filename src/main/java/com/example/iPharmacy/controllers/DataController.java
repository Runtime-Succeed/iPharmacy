package com.example.iPharmacy.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.QuestionSetRepository;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.database.UserInfoRepositoryTemplate;
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
	
	@Autowired
	private UserInfoRepositoryTemplate mongoTemplate;
	
	private QuestionSetRepository qsRepo;
	private UserInfoRepository userRepo;
	
	@Autowired
	public DataController(QuestionSetRepository qsRepo, UserInfoRepository userRepo) {
		this.qsRepo = qsRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping("/questionSet")
	public ResponseEntity<QuestionSet> getQuestionSet(Authentication auth, @RequestParam("id") String id) {
		UserInfo currentIdAndUsername = (UserInfo)auth.getPrincipal();
		QuestionSet qs = mongoTemplate.getAQuestionSet(currentIdAndUsername.getId(), id);
		if(qs == null)
			return new ResponseEntity<QuestionSet>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<QuestionSet>(qs, HttpStatus.OK);
	}
	
	@GetMapping("/questionSet/all")
	public List<QuestionSet> getAllQuestionSets() {
		return qsRepo.findAll();
	}
	
	@GetMapping("/htn-dosage-list")
	public QuestionSet getHtn(Authentication a) {
		System.out.println(((UserInfo)a.getPrincipal()).getUsername());
		return qsRepo.findFirstByTitle("HTN Dosage List");
	}
	
	@GetMapping("/titles")
	public String getTitles(Authentication auth) throws JsonProcessingException {
		UserInfo currentIdAndUsername = (UserInfo)auth.getPrincipal();
		UserInfo titlesWrapper = userRepo.findAllTitlesById(currentIdAndUsername.getId());
		ObjectMapper obj = new ObjectMapper().setSerializationInclusion(Include.NON_DEFAULT);
		return obj.writeValueAsString(titlesWrapper.getQuestionSets());	
	}
	
	@GetMapping("/users")
	public List<UserInfo> getUsers() {
		return userRepo.findAll();
	}
	
}
