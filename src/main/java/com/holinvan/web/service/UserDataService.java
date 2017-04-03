package com.holinvan.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.holinvan.web.model.User;
import com.holinvan.web.model.UserData;
import com.holinvan.web.repository.UserDataRepository;
import com.holinvan.web.repository.UserRepository;

@Service
public class UserDataService {
	
	@Autowired
	UserDataRepository userDataRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	public List <UserData> getUserData(){
		return userDataRepository.findAll();
	}
	
	@Transactional
	public void editUserInfo(User activeUser, UserData userData){
		User user = userRepository.findByUsername(activeUser.getUsername());
		userData.setUser(user);
		userDataRepository.save(userData);
	}
	
	@Transactional
	public boolean userAlreadyExists(UserData userData, BindingResult result){
		if (userDataRepository.findByEmail(userData.getEmail())==null){
			result.rejectValue("email", "userAlreadyExists");
			return false;
			
		}else{
			return true;
		}
	}
	
	@Transactional
	public User findUserById(Integer id) {
		return userRepository.findOne(id);
		
	}
}