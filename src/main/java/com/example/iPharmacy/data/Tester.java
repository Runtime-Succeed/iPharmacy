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
		
		Tester t = new Tester();
		t.example();
	}
	
	public void example() throws JsonProcessingException {
		
		//example #1: HTN_Dosage_List
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
				"Generic",
				new ArrayList<>(Arrays.asList("Brand", "Dose (mg)", "Max Dose (mg)")), 
				new ArrayList<>(Arrays.asList(q1, q2, q3)));
		
		ObjectMapper o = new ObjectMapper();
		String res = o.writerWithDefaultPrettyPrinter().writeValueAsString(qs);
		System.out.println(res);
		System.out.println(qs);
		
		//example #2: ABX
		Map<String, List<String>> map4 = new LinkedHashMap<>();
		map4.put("Generic", new ArrayList<>(Arrays.asList("Ampicillin")));
		map4.put("Classification", new ArrayList<>(Arrays.asList("PNC")));
		
		Map<String, List<String>> map5 = new LinkedHashMap<>();
		map5.put("Generic", new ArrayList<>(Arrays.asList("Piperacillin", "Tazobactam")));
		map5.put("Classification", new ArrayList<>(Arrays.asList("PNC")));
		
		Map<String, List<String>> map6 = new LinkedHashMap<>();
		map6.put("Generic", new ArrayList<>(Arrays.asList("Amoxicillin", "Clavulanate")));
		map6.put("Classification", new ArrayList<>(Arrays.asList("PNC")));
		
		Question q4 = new Question("Unasyn", map4);
		Question q5 = new Question("Zosyn", map5);
		Question q6 = new Question("Augmentin", map6);

		QuestionSet qs2 = new QuestionSet(
				"ABX", 
				28, 
				"Brand", 
				new ArrayList<>(Arrays.asList("Generic", "Classification")), 
				new ArrayList<>(Arrays.asList(q4, q5, q6)));
		
		String res2 = o.writerWithDefaultPrettyPrinter().writeValueAsString(qs2);
		System.out.println(res2);
		System.out.println(qs2);		
	}
}
