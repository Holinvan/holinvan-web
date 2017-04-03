package com.holinvan.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.holinvan.web.model.Camping;
import com.holinvan.web.repository.CampingRepository;

@Service
public class CampingService {
	
	@Autowired
	CampingRepository campingRepository; 
	
	@Transactional
	public List <Camping> getAllCampings(){
	return campingRepository.findAll();
	}
	
	@Transactional
	public Camping addNewCamping(Camping camping){
		return campingRepository.save(camping);
	}
	
}
