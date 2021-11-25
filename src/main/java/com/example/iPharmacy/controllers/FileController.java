package com.example.iPharmacy.controllers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileWriter;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.example.iPharmacy.utility.CsvToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.nio.file.Path;
import com.example.iPharmacy.utility.FileInfo;
import com.example.iPharmacy.utility.ResponseMessage;
import com.example.iPharmacy.utility.FileStorageService;

@Controller
@CrossOrigin("http://localhost:8081")
public class FileController {
	
	@Autowired
	private UserInfoRepository userRepo;

    @Autowired
    FileStorageService storageService;
    public static void deleteFolderfiles(File afile){
        for (File subFile : afile.listFiles()) {
            subFile.delete();
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(Authentication auth, @RequestParam("file") MultipartFile file) {
    	System.out.println("upload reached");
        UserInfo currentIDAndUsername = (UserInfo)auth.getPrincipal();
        String message = "";
        try {
            String nameFile = file.getOriginalFilename();
            deleteFolderfiles(new File("uploads"));
            String nameOfFile;
            char[] temp = nameFile.toCharArray();
            int num = temp.length;
            char[] y = new char[num - 4];
            for (int j = 0; j < y.length; j++) {
                y[j] = temp[j];
            }
            nameOfFile = new String(y);
            try {
                file.transferTo(Paths.get("uploads").resolve(nameFile));
                CsvToJson fUpload = new CsvToJson("uploads/" + nameFile, nameOfFile);
                QuestionSet qs = fUpload.convertFile();
                
                UserInfo currentUser = userRepo.findById(currentIDAndUsername.getId()).get();
                currentUser.addQuestionSet(qs);
                userRepo.save(currentUser);
                
                try {
                    File jFile = new File("src/main/resources/static/json/" + fUpload.fileName + ".json");
                    FileWriter myWriter = new FileWriter(jFile);
                    myWriter.write(fUpload.convertFile().toString());
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                storageService.deleteAll();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus. INTERNAL_SERVER_ERROR). build();
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
