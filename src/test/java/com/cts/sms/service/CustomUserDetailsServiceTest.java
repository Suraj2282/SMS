package com.cts.sms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.sms.entity.User;
import com.cts.sms.repository.UserRepository;

class CustomUserDetailsServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);		
	}
	
	@Test
	void loadByUsernameTest()
	{
		when(userRepository.findByUsername("admin")).thenReturn(User.builder().username("admin").password("admin").build());
		
		User user = userRepository.findByUsername("admin");
		assertNotNull(user);
	}

	

}
