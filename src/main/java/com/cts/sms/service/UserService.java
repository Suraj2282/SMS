package com.cts.sms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.sms.controller.UserController;
import com.cts.sms.entity.User;
import com.cts.sms.repository.UserRepository;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder bcp = new BCryptPasswordEncoder(12);
	
	@Autowired
	private JWTService jwtService;
	
	
	public User register(User user)
	{
		user.setPassword(bcp.encode(user.getPassword()));
		return userRepository.save(user);
	}


	public String verify(User user) {
		
		log.info("Verification started");
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		log.info("Verification done now checking for authentication");
		if(authentication.isAuthenticated())
		{
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			if(userDetails!=null)
			{
				log.info("User Details Found");
			}
			else
			{
				log.info("User Details not Found");
			}
			log.info("Authentication Successfull");
			return jwtService.generateToken(userDetails);
		}
		else
		{
			log.info("Verification Failed");
		}
		log.info("Something went wrong");
		return "Fail";
	}
}
