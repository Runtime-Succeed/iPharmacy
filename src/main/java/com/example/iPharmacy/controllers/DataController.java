package com.example.iPharmacy.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/questionSet")
	public ResponseEntity<QuestionSet> getQuestionSet(@RequestParam("id") String id) {
		try {
			return new ResponseEntity<QuestionSet>(qsRepo.findById(id).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			return new ResponseEntity<QuestionSet>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/questionSet/all")
	public List<QuestionSet> getAllQuestionSets() {
		return qsRepo.findAll();
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
