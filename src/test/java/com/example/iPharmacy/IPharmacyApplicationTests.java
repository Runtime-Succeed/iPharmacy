package com.example.iPharmacy;

import java.util.ArrayList;
import java.util.Arrays;

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

import com.example.iPharmacy.controllers.DBApplicationController;
import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.QuestionSetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DBApplicationController.class)
@WithMockUser
public class IPharmacyApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private QuestionSetRepository repo;
	
	@Test
	public void testGetExampleQuestionSet() throws Exception {
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get("/example/questionSet").accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		QuestionSet qs = new ObjectMapper().readValue(result.getResponse().getContentAsString(), QuestionSet.class);
		Assertions.assertEquals("HTN Dosage List", qs.getTitle());
		Assertions.assertEquals(30, qs.getRows());
		Assertions.assertEquals("Generic", qs.getQuestionAsk());
		Assertions.assertEquals(
				new ArrayList<String>(Arrays.asList("Brand", "Dose (mg)", "Max Dose (mg)")), 
				qs.getAnswerCols());
	}

	@Test
	public void contextLoads() {
	}

}
