package com.cts.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.sms.entity.User;
import com.cts.sms.entity.UserPrincipal;
import com.cts.sms.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepo.findByUsername(username);
		 if(user==null)
		 {
			 System.out.println("User Not Found");
			 throw new UsernameNotFoundException("User Not Found");
		 }
		
		return new UserPrincipal(user);
	}

}
