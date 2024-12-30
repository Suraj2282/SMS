package com.cts.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsApplication {

//	Creating the instance of logger
	private static final Logger log = LoggerFactory.getLogger(SmsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
		
		log.info("Welcome to School Management System");
		log.info("School Management System is started");
		
	}

	

}
