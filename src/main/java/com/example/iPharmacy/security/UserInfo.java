package com.example.iPharmacy.security;
import org.apache.commons.codec.binary.Hex;

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

public class UserInfo {
	
    private String userName;
    private String password;
    private String salt;
    private List<QuestionSet> questionSets;
    private static final int ITERATIONS = 10000;
    private static final int KEYLENGTH = 512;
    
    /**
     * 
     * @param aUserName
     * @param aPassword
     * @throws UnsupportedEncodingException
     */
    public UserInfo(String userName, String password) throws UnsupportedEncodingException {
    	
    	//initialize fields
        this.userName = userName;
        this.password = password;
        Random rand = new Random();
        salt = Integer.toString(rand.nextInt());
        questionSets = new ArrayList<>();
        
        //hash password
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, ITERATIONS, KEYLENGTH);
        this.password = Hex.encodeHexString(hashedBytes);
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	} */

	public List<QuestionSet> getQuestionSets() {
		return questionSets;
	}

	public void addQuestionSet(QuestionSet toAdd) {
		questionSets.add(toAdd);
	}

	private byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
    
    
    
}
