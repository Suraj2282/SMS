package com.cts.sms.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private String secretKey = "";
	
	public JWTService()
	{
		try
		{
//			Generate the jwt token using base 64
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sK = keyGen.generateKey(); //SecretKey is a predfined class here
			
//			store the key in secret key
			secretKey= Base64.getEncoder().encodeToString(sK.getEncoded());	
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	
//	Generating the token 
	public String generateToken(String username) {
	
		
		 Map<String, Object> claims = new HashMap<>();
		 
		 return Jwts.builder()
				 .claims()
				 .add(claims)
				 .subject(username)
				 .issuedAt(new Date(System.currentTimeMillis()))
				 .expiration(new Date(System.currentTimeMillis()+60*60*30))
				 .and()
				 .signWith(getKey())  //signed with base 64
				 .compact();
		 
		
	}

//	Returns the generated key
	private Key getKey() {
		byte[]keyBytes = Decoders.BASE64.decode(secretKey);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}


	public String extractUsername(String token) {
	
		return "";
	}


	public boolean validateToken(String token, UserDetails userDetails) {
		
		return true;
	}

}
