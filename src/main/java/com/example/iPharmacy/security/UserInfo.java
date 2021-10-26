package com.example.iPharmacy.security;

import org.apache.commons.codec.binary.Hex;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.iPharmacy.data.QuestionSet;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Document
public class UserInfo {

	@Id
	private String id;

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String salt;
	private List<QuestionSet> questionSets;
	
	private static final int ITERATIONS = 10000;
	private static final int KEYLENGTH = 512;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 * @throws UnsupportedEncodingException
	 */
	public UserInfo(String firstName, String lastName, String email, String username, String password)
			throws UnsupportedEncodingException {

		//initialize fields
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		Random rand = new Random();
		this.salt = Integer.toString(rand.nextInt());
		questionSets = new ArrayList<>();

		// hash password
		char[] passwordChars = password.toCharArray();
		byte[] saltBytes = salt.getBytes();
		byte[] hashedBytes = hashPassword(passwordChars, saltBytes);
		this.password = Hex.encodeHexString(hashedBytes);
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<QuestionSet> getQuestionSets() {
		return questionSets;
	}

	public void addQuestionSet(QuestionSet toAdd) {
		questionSets.add(toAdd);
	}

	public static byte[] hashPassword(final char[] password, final byte[] salt) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEYLENGTH);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			return res;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

}
