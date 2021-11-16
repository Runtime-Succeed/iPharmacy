package com.example.iPharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import com.example.iPharmacy.utility.FileStorageService;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}

