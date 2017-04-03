package com.holinvan.web.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.holinvan.web.helper.UserHelper;
import com.holinvan.web.model.User;
import com.holinvan.web.repository.UserRepository;
import com.holinvan.web.type.Role;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserHelper userHelper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public boolean findBySecurityCodeAndSetEnabled(String code) {
		if (userRepository.findBySecurityCode(code)!=null){
			User user = userRepository.findBySecurityCode(code);
			user.setEnabled(true);
			return true;
		}else{
			return false;
		}		
	}
	@Transactional
	public boolean userAlreadyExists(User user, BindingResult result) {
		if (userRepository.findByUsername(user.getUsername())!=null){
			result.rejectValue("username", "error.emailAlreadyExists");
			return true;
		}else{
			
			return false;
		}
	}
	@Transactional
	public void addNewUser(User userForm) {
		User user = new User(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()), Arrays.asList(new SimpleGrantedAuthority(Role.USER.getValue())));
		user.setEnabled(false);
		user.setSecurityCode(userHelper.generateAndSaveSecurityCode());
		userRepository.save(user);
	}
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}

