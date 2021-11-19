package com.example.iPharmacy.controllers;

import com.example.iPharmacy.utility.CsvToJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Controller
public class FileUploadController {
    static void deleteFolderfiles(File afile){
        for (File subFile : afile.listFiles()) {
                subFile.delete();
            }
    }

    @PostMapping("/fileUpload")
public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String nameFile = file.getOriginalFilename();
        //delete any previous csv files
        deleteFolderfiles(new File("uploads"));
        //obtain the name of the file without the type i.e. .csv
        String nameOfFile;
        char[] temp=nameFile.toCharArray();
        int num= temp.length; char[]y=new char[num-4];
        for(int j=0;j<y.length;j++){
            y[j]=temp[j];
        };
        nameOfFile=new String(y);

        try {
            file.transferTo(Paths.get("uploads").resolve(nameFile));

            CsvToJson fUpload=new CsvToJson("uploads/"+nameFile, nameOfFile);
            fUpload.convertFile();
            try {
                //write json file to json folder
                File jFile= new File("src/main/resources/static/json/"+fUpload.fileName+".json");

                FileWriter myWriter = new FileWriter(jFile);
                myWriter.write(fUpload.convertFile().toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus. INTERNAL_SERVER_ERROR). build();
        }

        return ResponseEntity.ok("File uploaded successfully.");
    }

}