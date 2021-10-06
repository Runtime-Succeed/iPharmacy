package com.example.iPharmacy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.iPharmacy.database.DBApplicationController;
import com.example.iPharmacy.database.Document;
import com.example.iPharmacy.database.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class IPharmacyApplicationTests {
	
	private MockMvc mvc;
	
	@Mock
	private DocumentRepository repo;
	
	@Before
	public void setup() {
		System.out.println("before...");
		mvc = MockMvcBuilders.standaloneSetup(new DBApplicationController(repo)).build();
	}

	@After
	public void cleanup() {
		System.out.println("after...");
	}
	
	@Test
	public void testGetNumberOfDocuments() throws Exception {
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get("/test-document").accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		Document doc = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Document.class);
		Assert.assertEquals("d1", doc.getId());
	}

	@Test
	public void documentTest(){
		testDocument test = new testDocument("C:\\Users\\Jay\\Downloads\\HTN_Dosage_List.csv");
		int size = test.questions.size();
		Assert.assertEquals(30, size);
	}

	@Test
	public void contextLoads() {
	}



}
