package com.example.iPharmacy.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.Question;
import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.CustomUserInfoRepository;
import com.example.iPharmacy.security.UserInfo;

/**
 * Use this class for testing or manually editing data
 */
@RestController
public class TestingController {
	
	@Autowired
	private CustomUserInfoRepository customUserRepo;

	@Autowired
	private CustomUserInfoRepository userRepo;
	
	// Deletes All Users
	/*
	 * Commented to make sure it is not accidentally used
	@GetMapping("/delete/users/all")
	public String deleteAllUsers() {
		userRepo.deleteAll();
		return "All Users Deleted. User Count: " + userRepo.count() ;
	}*/

	// Manual Upload
	/*
	 * Commented to make sure it is not accidentally used
	 * Creates a user with all the question sets loaded
	@GetMapping("/manual/uploadUserQuestionSets2")
	public String uploadQuestionSets2() throws IOException {
		
		String basePath = "BASE_PATH";
		
		UserInfo user = new UserInfo("Afirstname", "Alastname", "email@email.com", "user1", "password1");
		
		CsvToJson c = new CsvToJson(basePath + "HTN_Dosage_List.csv","HTN Dosage List");
        QuestionSet qs = c.convertFile();
        user.addQuestionSet(qs);
        
		c = new CsvToJson(basePath + "ABX.csv", "ABX");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "ABX 3.csv", "ABX3");
		qs = c.convertFile();
        user.addQuestionSet(qs);
        
		c = new CsvToJson(basePath + "Asthma Drugs.csv", "Asthma Drugs");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "Drug List.csv", "Drug List");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "Epilepsy.csv", "Epilepsy");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "Integration Block.csv", "Integration Block");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "OP Drugs.csv", "OP Drugs");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "Pulmonary HTN.csv", "Pulmonary HTN");
		qs = c.convertFile();
        user.addQuestionSet(qs);
		
		c = new CsvToJson(basePath + "RA Drugs.csv", "RA Drugs");
		qs = c.convertFile();
        user.addQuestionSet(qs);

        userRepo.insert(user);
        System.out.println("Data uploaded manually");
        return "Data uploaded manually";
	}*/
	
	/*
	 * Creates a user with just two question sets
	@GetMapping("/manual/uploadUser")
	public String uploadUser() throws IOException {
		UserInfo newUser = new UserInfo("Bob", "Smith", "email@email.com", "username1", "password1");
		CsvToJson c = new CsvToJson("C:\\Users\\az463\\Desktop\\HTN_Dosage_List.csv", "HTN Dosage List");
		QuestionSet qs = c.convertFile();
		newUser.addQuestionSet(qs);
		c = new CsvToJson("C:\\Users\\az463\\Desktop\\ABX.csv", "ABX");
		qs = c.convertFile();
		newUser.addQuestionSet(qs);
		userRepo.insert(newUser);
		
		System.out.println("User uploaded manually");
		return "User uploaded manually";
	}*/
	
	// Manual Upload Question Sets
	/*
	 * Uploads all question sets
	 * Commented to make sure it is not accidentally used
	@GetMapping("/manual/uploadQuestionSets")
	public String uploadQuestionSets() throws IOException {
		
		String basePath = "BASE_PATH";
		
		CsvToJson c = new CsvToJson(basePath + "HTN_Dosage_List.csv","HTN Dosage List");
        QuestionSet qs = c.convertFile();
        qsRepo.insert(qs);

		c = new CsvToJson(basePath + "ABX.csv", "ABX");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "ABX 3.csv", "ABX3");
		qs = c.convertFile();
        qsRepo.insert(qs);
        
		c = new CsvToJson(basePath + "Asthma Drugs.csv", "Asthma Drugs");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "Drug List.csv", "Drug List");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "Epilepsy.csv", "Epilepsy");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "Integration Block.csv", "Integration Block");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "OP Drugs.csv", "OP Drugs");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "Pulmonary HTN.csv", "Pulmonary HTN");
		qs = c.convertFile();
        qsRepo.insert(qs);
		
		c = new CsvToJson(basePath + "RA Drugs.csv", "RA Drugs");
		qs = c.convertFile();
        qsRepo.insert(qs);
        
        System.out.println("Data uploaded manually");
        return "Data uploaded manually";

	} */
	
	/*
	@GetMapping("deleteQ")
	public String deleteAll()
	{
		qsRepo.deleteAll();
		return "Deleted all Qs";
	}*/

	// used for unit testing
	@GetMapping("/example/questionSet")
	public QuestionSet getExampleQuestionSet() {
		return exampleData();
	}

	private QuestionSet exampleData() {

		// example #1: HTN_Dosage_List
		Map<String, List<String>> map1 = new LinkedHashMap<>();
		map1.put("Brand", new ArrayList<>(Arrays.asList("Thalitone")));
		map1.put("Dose (mg)", new ArrayList<>(Arrays.asList("25 QD")));
		map1.put("Max Dose (mg)", new ArrayList<>(Arrays.asList("100 QD")));

		Question q1 = new Question("Chlorthalidone", map1);

		Map<String, List<String>> map2 = new LinkedHashMap<>();
		map2.put("Brand", new ArrayList<>(Arrays.asList("Prinivil", "Zestril", "Qbrelis")));
		map2.put("Dose (mg)", new ArrayList<>(Arrays.asList("10-40 QD")));
		map2.put("Max Dose (mg)", new ArrayList<>(Arrays.asList("40 QD")));

		Question q2 = new Question("Lisinopril", map2);

		Map<String, List<String>> map3 = new LinkedHashMap<>();
		map3.put("Brand", new ArrayList<>(Arrays.asList("-1")));
		map3.put("Dose (mg)", new ArrayList<>(Arrays.asList("10-50 TID-QID")));
		map3.put("Max Dose (mg)", new ArrayList<>(Arrays.asList("200 QD")));

		Question q3 = new Question("Hydralazine", map3);

		QuestionSet qs = new QuestionSet("HTN Dosage List", 30, "Generic",
				new ArrayList<>(Arrays.asList("Brand", "Dose (mg)", "Max Dose (mg)")),
				new ArrayList<>(Arrays.asList(q1, q2, q3)));
		return qs;
	}

}
