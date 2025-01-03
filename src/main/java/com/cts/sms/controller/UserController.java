package com.cts.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.sms.entity.User;
import com.cts.sms.service.UserService;

@RestController
//@RequestMapping("/access")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User register(@RequestBody User user)
	{
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user)
	{
		log.info("Login Process Started");
		
		return userService.verify(user);
	}
	
	
	
}
