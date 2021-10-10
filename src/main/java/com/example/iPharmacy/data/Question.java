package com.example.iPharmacy.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Question {
	
	private String questionText;
	private Map<String, List<String>> answers;
	
	/**
	 * 
	 * @param questionText
	 * @param answers
	 */
	public Question(String questionText, Map<String, List<String>> answers) {
		
		this.questionText = questionText;
		this.answers = new LinkedHashMap<>(answers);	//shallow copy
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Map<String, List<String>> getAnswers() {
		return new LinkedHashMap<>(answers);
	}

	public void setAnswers(Map<String, List<String>> answers) {
		this.answers = new LinkedHashMap<>(answers);
	}
	
	
}