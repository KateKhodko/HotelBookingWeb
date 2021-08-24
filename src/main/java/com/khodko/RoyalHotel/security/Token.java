package com.khodko.RoyalHotel.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import com.khodko.RoyalHotel.config.Config;


public class Token {
		
	private String value;
	
	public Token(String value) {
		this.value = value;
	}
	
	public Token() {
		value = generateToken();
	}

	public String getValue() {
		return value;
	}
	
	public String getHash() {
		return hash(value + Config.SECRET);
	}
	
	public static String hash(String str) {        
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
	        md.update(str.getBytes());
	        byte[] mdArray = md.digest();
	        StringBuilder sb = new StringBuilder(mdArray.length * 2);
	        for (byte b : mdArray) {
	            int v = b & 0xff;
	            if (v < 16) {
	                sb.append('0');
	            }
            sb.append(Integer.toHexString(v));
	        }  
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}     
        return str;        
    }
    
    public static String generateToken() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
