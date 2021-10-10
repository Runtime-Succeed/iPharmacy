package com.example.iPharmacy.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QuestionSet {
	
	private String title;
	private int rows;
	private String questionAsk;
	private List<String> answerCols;
	private List<Question> questions;
	
	/**
	 * 
	 * @param title
	 * @param rows
	 * @param questionAsk
	 * @param answerCols
	 * @param questions
	 */
	public QuestionSet(String title, int rows, String questionAsk, List<String> answerCols, List<Question> questions) {
		
		this.title = title;
		this.rows = rows;
		this.questionAsk = questionAsk;
		this.answerCols = new ArrayList<>(answerCols);		
		this.questions = new ArrayList<>(questions);	//shallow copy
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getQuestionAsk() {
		return questionAsk;
	}

	public void setQuestionAsk(String questionAsk) {
		this.questionAsk = questionAsk;
	}

	public List<String> getAnswerCols() {
		return new ArrayList<>(answerCols);
	}

	public void setAnswerCols(List<String> answerCols) {
		this.answerCols = new ArrayList<>(answerCols);
	}

	public List<Question> getQuestions() {
		return new ArrayList<>(questions);
	}

	public void setQuestions(List<Question> questions) {
		this.questions = new ArrayList<>(questions);
	}

	@Override
	public String toString() {
		
		String s = "";
		s += "Title: " + title + "\n";
		s += "Rows: " + rows + "\n";
		s += "Question asking for: " + questionAsk + "\n";
		s += "Answer columns: " + String.join(", ", answerCols) + "\n";
		s += "Questions: \n";
		for(Question q : questions) {
			
			s += "\tQuestion Text: " + q.getQuestionText() + "\n";
			Map<String, List<String>> answers = q.getAnswers();
			for(Entry<String, List<String>> entry : answers.entrySet())
			{
				s += "\t" + entry.getKey() + ": " + String.join(", ", entry.getValue()) + "\n";
			}
			s += "\n";
		}
		
		return s;
	}	
}
