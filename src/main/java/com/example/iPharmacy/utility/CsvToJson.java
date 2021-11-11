package com.example.iPharmacy.utility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.iPharmacy.data.QuestionSet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CsvToJson {

    private String filePath;
    private String fileName;

    public CsvToJson(String aFilePath, String aFileName){
        filePath = aFilePath;
        fileName = aFileName;
    }

    public QuestionSet convertFile() throws IOException{
        String line = "";
        String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        int numRows = countRows();
        JSONObject jsonObject = new JSONObject();
        BufferedReader firstLine = new BufferedReader(new FileReader(filePath));
        line = firstLine.readLine();
        firstLine.close();
        String[] firstRow = line.split(splitBy);
        JSONArray answerCols = new JSONArray();
        for(int i=1; i<firstRow.length; i++){
            answerCols.add(firstRow[i]);
        }
        jsonObject.put("title", fileName);
        jsonObject.put("rows", numRows);
        jsonObject.put("questionAsk", firstRow[0]);
        jsonObject.put("answerCols", answerCols);
        JSONArray questions = new JSONArray();
        line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int count = 0;
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                if(count != 0) {
                    String[] row = line.split(splitBy);    // use comma as separator
                    JSONObject question = new JSONObject();
                    JSONObject answers = new JSONObject();
                    question.put("questionText", row[0]);
                    for (int i=1; i<row.length; i++) {
                        JSONArray temp = new JSONArray();
                        String removeQuotes = row[i].replace("\"", "");
                        temp.add(removeQuotes);
                        answers.put(firstRow[i], temp);
                    }
                    question.put("answers", answers);
                    questions.add(question);
                }
                count++;
            }
            jsonObject.put("questions", questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ObjectMapper().readValue(jsonObject.toString(), QuestionSet.class);
    }

    public int countRows() throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            is.close();
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        CsvToJson c = new CsvToJson("C:\\Users\\az463\\Desktop\\HTN_Dosage_List.csv","HTN_Dosage_List");
        System.out.println(c.convertFile());
        /*try {
            FileWriter myWriter = new FileWriter(c.fileName+".json");
            myWriter.write(c.convertFile().toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/
    }
}