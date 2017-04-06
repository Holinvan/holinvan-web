package com.holinvan.web.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.holinvan.web.model.User;
import com.holinvan.web.repository.UserRepository;
import com.holinvan.web.type.Role;

public final class SignInService implements SignInAdapter {
	
	@Autowired
    private UserRepository userRepository;
	
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		User userToAuthenticate = new User();
		User userFound = userRepository.findByUsername(userId);
		if (userFound != null){
			userToAuthenticate = userFound;
		}
		else {
			userToAuthenticate = new User(userId, randomAlphabetic(8), Arrays.asList(new SimpleGrantedAuthority(Role.USER.getValue())));
		}
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userToAuthenticate, null, Arrays.asList(new SimpleGrantedAuthority(Role.USER.getValue()))));	
		return null;
	}

}
