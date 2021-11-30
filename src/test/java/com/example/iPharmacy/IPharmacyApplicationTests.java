package com.example.iPharmacy;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.iPharmacy.security.UserInfo;

@SpringBootTest
public class IPharmacyApplicationTests {

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
