package com.example.iPharmacy;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.iPharmacy.data.testDocument;
import com.example.iPharmacy.database.DBApplicationController;
import com.example.iPharmacy.database.Document;
import com.example.iPharmacy.database.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DBApplicationController.class)
@WithMockUser
public class IPharmacyApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DocumentRepository repo;
	
	@Test
	public void testGetNumberOfDocuments() throws Exception {
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get("/test-document").accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		Document doc = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Document.class);
		Assertions.assertEquals("d1", doc.getId());
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

	@Test
	public void checkTest1()  {
		HelloController h = new HelloController();
		Boolean b = h.checkAnswer("thalitdone");
		Assertions.assertEquals(true,b);
	}

	@Test
	public void checkTest2()  {
		HelloController h = new HelloController();
		Boolean b = h.checkAnswer("ThalitdonE ");
		Assertions.assertEquals(true,b);
	}

	@Test
	public void checkTest3()  {
		HelloController h = new HelloController();
		Boolean b = h.checkAnswer("THALITDONEe");
		Assertions.assertEquals(false,b);
	}

}
