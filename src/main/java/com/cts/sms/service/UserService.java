package com.cts.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.sms.entity.User;
import com.cts.sms.repository.UserRepository;

@Service
public class UserService {

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
		
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated())
		{
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return jwtService.generateToken(userDetails);
		}
		return "Fail";
	}
}
