package com.cts.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.sms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	
}
