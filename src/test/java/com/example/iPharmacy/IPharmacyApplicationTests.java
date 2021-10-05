package com.example.iPharmacy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IPharmacyApplicationTests {

	public static void main(String[] args){
		testDocument test = new testDocument("C:\\Users\\Jay\\Downloads\\HTN_Dosage_List.csv");
		int size = test.questions.size();
		if(size == 30)
			System.out.println("Correct");
		else throw new RuntimeException("Incorrect");
	}

	@Test
	void contextLoads() {
	}



}
