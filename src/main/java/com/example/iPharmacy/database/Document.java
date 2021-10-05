package com.example.iPharmacy.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.data.annotation.Id;

public class Document {
	
	@Id
	private String id;
	
	private byte[] data;
	
	public Document() {};
	
	public Document(byte[] data) {
		this.data = data.clone();
	}
	
	public Document(String path) throws IOException {
		data = Files.readAllBytes(Path.of(path));
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
}