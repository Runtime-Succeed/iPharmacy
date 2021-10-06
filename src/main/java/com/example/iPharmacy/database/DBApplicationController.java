package com.example.iPharmacy.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBApplicationController {
	
	private DocumentRepository repository;
	
	@Autowired
	public DBApplicationController(DocumentRepository repository) {
		this.repository = repository;		
	}
	
	@GetMapping(value = "/data", produces = "text/plain")
	public String getData() {
		String s = "";
		List<Document> docs = repository.findAll();
		for(Document d : docs)
			s += new String(d.getData(), StandardCharsets.UTF_8) + "\n\n";	//problem if something is not UTF_8 encoded
		return s;
	}
	
	//need front-end connection to test
	@PostMapping("/upload")
	public void uploadData(@RequestBody Document doc) {
		repository.insert(doc);
	}
	
	//eventually change to POST, GET for testing
	@GetMapping("/upload/file")
	public void uploadFileData() {
		try {
			Document doc = new Document("LOCAL_PATH_TO_FILE");
			repository.insert(doc);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/test-document")
	public Document getDocument() {
		Document d1 = new Document();
		d1.setId("d1");
		return d1;
	}
	
	

}
