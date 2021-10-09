package com.example.iPharmacy.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tester {
	
	public static void main(String[] args) throws JsonProcessingException {
		
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
		
		
		QuestionSet qs = new QuestionSet(
				"HTN Dosage List", 
				30, 
				new ArrayList<>(Arrays.asList("Generic", "Brand", "Dose (mg)", "Max Dose (mg)")), 
				new ArrayList<>(Arrays.asList(q1, q2, q3)));
		
		ObjectMapper o = new ObjectMapper();
		String res = o.writerWithDefaultPrettyPrinter().writeValueAsString(qs);
		System.out.println(res);
		System.out.println(qs);
		
		/*
		 * Second example, need to switch arrays to lists
		 * 
		 * Map<String, String[]> map4 = new HashMap<>();
		map4.put("Generic", new String[] {"Ampicillin"});
		map4.put("Classification", new String[] {"PNC"});
		
		Map<String, String[]> map5 = new HashMap<>();
		map5.put("Generic", new String[] {"Piperacillin", "Tazobactam"});
		map5.put("Classification", new String[] {"PNC"});
		
		Map<String, String[]> map6 = new HashMap<>();
		map6.put("Generic", new String[] {"Amoxicillin", "Clavulanate"});
		map6.put("Classification", new String[] {"PNC"});
		
		Question[] questions = new Question[3];
		questions[0] = new Question("Unasyn", map4);
		questions[1] = new Question("Zosyn", map5);
		questions[2] = new Question("Augmentin", map6);

		QuestionSet qs2 = new QuestionSet("ABX", 28, new String[] {"Brand", "Generic", "Classification"}, questions);
		
		String res2 = o.writerWithDefaultPrettyPrinter().writeValueAsString(qs2);
		System.out.println(res2);
		System.out.println(qs2);*/		
	}
}
