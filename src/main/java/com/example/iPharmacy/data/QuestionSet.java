package com.example.iPharmacy.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QuestionSet {
	
	private String title;
	private int rows;
	private List<String> columns;
	private List<Question> questions;
	
	/**
	 * 
	 * @param title
	 * @param rows
	 * @param columns
	 * @param questions
	 */
	public QuestionSet(String title, int rows, List<String> columns, List<Question> questions) {
		
		this.title = title;
		this.rows = rows;
		this.columns = new ArrayList<>(columns);		
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

	public List<String> getColumns() {
		return new ArrayList<>(columns);
	}

	public void setColumns(List<String> columns) {
		this.columns = new ArrayList<>(columns);
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
		s += "Columns: " + String.join(", ", columns) + "\n";
		s += "Questions: \n";
		for(Question q : questions) {
			
			s += "\tQuestion Text: " + q.getqText() + "\n";
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
