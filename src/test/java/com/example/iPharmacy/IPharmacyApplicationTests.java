package com.example.iPharmacy;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.iPharmacy.controllers.TestingController;
import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.database.QuestionSetRepository;
import com.example.iPharmacy.database.UserInfoRepository;
import com.example.iPharmacy.security.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TestingController.class)
public class IPharmacyApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private QuestionSetRepository qsRepo;
	
	@MockBean
	private UserInfoRepository userRepo;
	
	/**
	 * Ensures GET endpoint is reachable and QuestionSet structure works properly
	 * @throws Exception
	 */
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

	/**
	 * Ensures UserInfo hashes passwords correctly
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testHashPassword() throws UnsupportedEncodingException {
		
		String password = "@s3Hx$dYl7eG";
		UserInfo aUser = new UserInfo(	"First Name",
										"Last Name",
										"email@email.com",
										"username1",
										password);
		String hashedPassword = aUser.getPassword();
		String salt = aUser.getSalt();
		Assertions.assertEquals(hashedPassword, 
				Hex.encodeHexString(UserInfo.hashPassword(password.toCharArray(), salt.getBytes()))); 
		
	}
	
}
