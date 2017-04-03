package com.holinvan.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.holinvan.web.model.User;
import com.holinvan.web.model.UserFacturationData;
import com.holinvan.web.repository.UserFacturationDataRepository;
import com.holinvan.web.repository.UserRepository;

@Service
public class UserFacturationDataService {
	
	private static Logger LOG = LoggerFactory.getLogger(UserFacturationDataService.class);
	
	@Autowired
	UserFacturationDataRepository userFacturationDataRepository;
	
	@Autowired
	UserRepository userRepository;

	@Transactional
	public void editFacturationUserInfo(User activeUser, UserFacturationData userFacturationData) {
		User user = userRepository.findByUsername(activeUser.getUsername());
		userFacturationData.setUser(user);
		UserFacturationData userFacturationDataAdded = userFacturationDataRepository.save(userFacturationData);
		activeUser.setUserFacturationData(userFacturationDataAdded);
		LOG.info("Facturation-Data added: " + userFacturationDataAdded);
	}

}
