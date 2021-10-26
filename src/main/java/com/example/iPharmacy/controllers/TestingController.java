package com.example.iPharmacy.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.iPharmacy.data.Question;
import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.QuestionSetRepository;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.example.iPharmacy.utility.CsvToJson;

/**
 * Use this class for testing or manually editing data
 */
@RestController
public class TestingController {

	private QuestionSetRepository qsRepo;
	private UserInfoRepository userRepo;

	@Autowired
	public TestingController(QuestionSetRepository qsRepo, UserInfoRepository userRepo) {
		this.qsRepo = qsRepo;
		this.userRepo = userRepo;
	}

	@GetMapping(value = "/data/text", produces = "text/plain")
	public String getData() {
		String s = "";
		List<QuestionSet> questionSets = qsRepo.findAll();
		for(QuestionSet qs : questionSets)
			s += qs + "\n\n";
		return s;
	}

	// Manual Upload
	@GetMapping("/manual/uploadUser")
	public void uploadUser() throws IOException {
		UserInfo newUser = new UserInfo("Bob", "Smith", "email@email.com", "username1", "password1");
		CsvToJson c = new CsvToJson("C:\\Users\\az463\\Desktop\\HTN_Dosage_List.csv", "HTN Dosage List");
		QuestionSet qs = c.convertFile();
		newUser.addQuestionSet(qs);
		c = new CsvToJson("C:\\Users\\az463\\Desktop\\ABX.csv", "ABX");
		qs = c.convertFile();
		newUser.addQuestionSet(qs);
		userRepo.insert(newUser);
	}

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
