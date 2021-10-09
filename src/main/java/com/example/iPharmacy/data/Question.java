package com.example.iPharmacy.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Question {
	
	private String qText;
	private Map<String, List<String>> answers;
	
	/**
	 * 
	 * @param qText
	 * @param answers
	 */
	public Question(String qText, Map<String, List<String>> answers) {
		
		this.qText = qText;
		this.answers = new LinkedHashMap<>(answers);	//shallow copy
	}

	public String getqText() {
		return qText;
	}

	public void setqText(String qText) {
		this.qText = qText;
	}

	public Map<String, List<String>> getAnswers() {
		return new LinkedHashMap<>(answers);
	}

	public void setAnswers(Map<String, List<String>> answers) {
		this.answers = new LinkedHashMap<>(answers);
	}
}