//package com.example.iPharmacy.database;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.iPharmacy.data.Question;
//import com.example.iPharmacy.data.QuestionSet;
//
//@RestController
//public class DBApplicationController {
//
//	private QuestionSetRepository repository;
//
//	@Autowired
//	public DBApplicationController(QuestionSetRepository repository) {
//		this.repository = repository;
//	}
//
//	@GetMapping(value = "/data", produces = "text/plain")
//	public String getData() {
//		String s = "";
//		List<QuestionSet> questionSets = repository.findAll();
//		for(QuestionSet qs : questionSets)
//			s += qs + "\n\n";
//		return s;
//	}
//
//	//need front-end connection to test
//	@PostMapping("/upload")
//	public void uploadData(@RequestBody QuestionSet qs) {
//		repository.insert(qs);
//	}
//
//	//manual upload
//	//eventually change to POST, GET for testing
//	@GetMapping("/example/upload")
//	public void uploadFileData() {
//		repository.insert(exampleData());
//	}
//
//	//used for unit testing
//	@GetMapping("/example/questionSet")
//	public QuestionSet getExampleQuestionSet() {
//		return exampleData();
//	}
//
//	private QuestionSet exampleData() {
//
//		//example #1: HTN_Dosage_List
//		Map<String, List<String>> map1 = new LinkedHashMap<>();
//		map1.put("Brand", new ArrayList<>(Arrays.asList("Thalitone")));
//		map1.put("Dose (mg)", new ArrayList<>(Arrays.asList("25 QD")));
//		map1.put("Max Dose (mg)", new ArrayList<>(Arrays.asList("100 QD")));
//
//		Question q1 = new Question("Chlorthalidone", map1);
//
//		Map<String, List<String>> map2 = new LinkedHashMap<>();
//		map2.put("Brand", new ArrayList<>(Arrays.asList("Prinivil", "Zestril", "Qbrelis")));
//		map2.put("Dose (mg)", new ArrayList<>(Arrays.asList("10-40 QD")));
//		map2.put("Max Dose (mg)", new ArrayList<>(Arrays.asList("40 QD")));
//
//		Question q2 = new Question("Lisinopril", map2);
//
//		Map<String, List<String>> map3 = new LinkedHashMap<>();
//		map3.put("Brand", new ArrayList<>(Arrays.asList("-1")));
//		map3.put("Dose (mg)", new ArrayList<>(Arrays.asList("10-50 TID-QID")));
//		map3.put("Max Dose (mg)", new ArrayList<>(Arrays.asList("200 QD")));
//
//		Question q3 = new Question("Hydralazine", map3);
//
//
//		QuestionSet qs = new QuestionSet(
//				"HTN Dosage List",
//				30,
//				"Generic",
//				new ArrayList<>(Arrays.asList("Brand", "Dose (mg)", "Max Dose (mg)")),
//				new ArrayList<>(Arrays.asList(q1, q2, q3)));
//		return qs;
//
//	}
//
//}
