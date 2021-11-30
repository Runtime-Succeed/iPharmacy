package com.example.iPharmacy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.CustomUserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Main controller for retrieving data
 */
@RestController
@RequestMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
	
	@Autowired
	private CustomUserInfoRepository customUserRepo;
	
	@GetMapping("/questionSet")
	public ResponseEntity<QuestionSet> getAQuestionSet(Authentication auth, @RequestParam("id") String id) {
		UserInfo currentIdAndUsername = (UserInfo)auth.getPrincipal();
		QuestionSet qs = customUserRepo.getAQuestionSet(currentIdAndUsername.getId(), id);
		if(qs == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(qs, HttpStatus.OK);
	}
	
	@GetMapping("/titles")
	public ResponseEntity<String> getTitlesAndIds(Authentication auth) throws JsonProcessingException {
		UserInfo currentIdAndUsername = (UserInfo)auth.getPrincipal();
		List<QuestionSet> titles = customUserRepo.findAllTitlesById(currentIdAndUsername.getId());
		if(titles == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			ObjectMapper obj = new ObjectMapper().setSerializationInclusion(Include.NON_DEFAULT);
			String titlesAndIds = obj.writeValueAsString(titles);	
			return new ResponseEntity<>(titlesAndIds, HttpStatus.OK);
		}
	}
	
}
