package com.example.iPharmacy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IPharmacyApplicationTests {

	@Test
	void documentTest(){
		testDocument test = new testDocument("C:\\Users\\Jay\\Downloads\\HTN_Dosage_List.csv");
		int size = test.questions.size();
		Assert.assertEquals(30, size);
	}

	@Test
	void contextLoads() {
	}



}
