package com.example.iPharmacy.data;
import java.util.*;
import java.io.*;

//converted to QuestionSet and Question classes
public class testDocument {
    public ArrayList<question> questions = new ArrayList<>();

    class question {
        private ArrayList<String[]> answers = new ArrayList<>();
        private String questionName;

        public question(String[] content) {
            questionName = content[0];
            for (int i=1; i<content.length; i++) {
                String[] temp = content[i].split(",");
                answers.add(temp);
            }
        }
    }

    public testDocument(String filePath){
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int count = 0;
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                if(count != 0) {
                    String[] row = line.split(splitBy);    // use comma as separator
                    question newQuestion = new question(row);
                    questions.add(newQuestion);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

