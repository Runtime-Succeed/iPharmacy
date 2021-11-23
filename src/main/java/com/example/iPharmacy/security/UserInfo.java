package com.example.iPharmacy.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.iPharmacy.data.QuestionSet;

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
	private static final String HASHALGORITHM = "PBKDF2WithHmacSHA512";
	
	/**
	 * For deserialization 
	 */
	public UserInfo() {}
	
	/**
	 * For creating Principal in Authentication
	 * @param id
	 * @param username
	 */
	public UserInfo(String id, String username) {
		this.id = id;
		this.username = username;
	}

	/**
	 * Creates new user with random salt and hashes password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 * @throws UnsupportedEncodingException
	 */
	public UserInfo(String firstName, String lastName, String email, String username, String password)
			throws UnsupportedEncodingException {
		System.out.println("uhoh");
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		questionSets = new ArrayList<>();
		setPassword(password);
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
		
		generateSalt();
		
		//hash password
		char[] passwordChars = password.toCharArray();
		byte[] saltBytes = salt.getBytes();
		byte[] hashedBytes = hashPassword(passwordChars, saltBytes);
		this.password = Hex.encodeHexString(hashedBytes);
	}

	public String getSalt() {
		return salt;
	}

	public List<QuestionSet> getQuestionSets() {
		return questionSets == null ? null : new ArrayList<>(questionSets);
	}

	public void addQuestionSet(QuestionSet toAdd) {
		questionSets.add(toAdd);
	}

	public static byte[] hashPassword(final char[] password, final byte[] salt) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance(HASHALGORITHM);
			PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEYLENGTH);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			return res;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	private void generateSalt() {
		this.salt = Integer.toString(new Random().nextInt());
	}

}
