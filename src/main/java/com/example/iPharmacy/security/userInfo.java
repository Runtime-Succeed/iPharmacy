package com.example.iPharmacy.security;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class userInfo {
    private String userName;
    private String password;
    private String salt;
    public userInfo(String aUserName, String aPassword) throws UnsupportedEncodingException {
        Random rand = new Random();
        userName = aUserName;
        password = aPassword;
        salt = Integer.toString(rand.nextInt());
        int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
        password = Hex.encodeHexString(hashedBytes);
    }

    public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
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

    public static void main(String[] args) throws UnsupportedEncodingException{
        userInfo test =  new userInfo("gordan402", "password");
        System.out.println(test.password);
    }
}
