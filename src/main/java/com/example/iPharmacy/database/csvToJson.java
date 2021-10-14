package com.example.iPharmacy.database;
import java.util.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class csvToJson {
    private String filePath;
    private String fileName;
    public csvToJson(String aFilePath, String aFileName){
        filePath = aFilePath;
        fileName = aFileName;
    }

    public void convertFile() throws IOException{
        String line = "";
        String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        int numRows = countRows()-1;
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
        try {
            FileWriter file = new FileWriter("C:\\Users\\Jay\\Desktop\\"+fileName+".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
}
