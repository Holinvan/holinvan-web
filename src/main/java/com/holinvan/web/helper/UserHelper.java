package com.holinvan.web.helper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.holinvan.web.repository.UserRepository;

@Component
public class UserHelper {
	
	@Autowired
	UserRepository userRepository;

	@Transactional
	public String generateAndSaveSecurityCode() {
		String securityCode = UUID.randomUUID().toString();
		return securityCode;
	}
}
	
	