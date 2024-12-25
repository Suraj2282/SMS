package com.cts.sms.service;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTService {

	private final static Logger log = LoggerFactory.getLogger(JWTService.class);
	
    private String secretkey = "";

    
    public JWTService() {

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
            log.info("Secret key generated");
        } catch (NoSuchAlgorithmException e) {
        	log.info("Exception in creation of secret key");
            throw new RuntimeException(e);
            
        }
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
//        log.info("Key Generated");
//        return Jwts.builder()
//                .claims()
//                .add(claims)
//                .subject(username)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //1 Hour
//                .and()
//                .signWith(getKey())
//                .compact();
        
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList()));
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
                .signWith(getKey())
                .compact();
    }

        

    

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        log.info(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
    	log.info("Username extracted");
    	log.info(extractClaim(token, Claims::getSubject));
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        log.info("Claims extracted");
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
    	log.info("Extracting all the claims");
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        log.info(userName);
        log.info("Token is validated");
        log.info(userDetails.getUsername());
        log.info("Username is"+userDetails.getUsername());
        boolean tokenResult= !isTokenExpired(token);
        if(tokenResult)
        {
        	log.info("token is valid");
        }
        else
        {
        	log.info("Token is invalid");
        }
        boolean result=userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
      
        return result;
    }

    private boolean isTokenExpired(String token) {
    	log.info("Validating the expiry of the token");
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
    	log.info("Extracting the expiration");
        return extractClaim(token, Claims::getExpiration);
    }

}
