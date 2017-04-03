package com.holinvan.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.holinvan.web.model.Availability;
import com.holinvan.web.model.Caravan;
import com.holinvan.web.model.Price;
import com.holinvan.web.repository.AvailabilityRepository;
import com.holinvan.web.repository.CaravanFactory;
import com.holinvan.web.repository.PriceRepository;

@Service
public class Caravanaservice {

	@Autowired
	CaravanFactory caravanFactory;
	
	@Autowired
	AvailabilityRepository availabilityRepository;
	
	@Autowired
	PriceRepository priceRepository;
	
	@Transactional
	public boolean caravanaAlreadyExists(Caravan caravana,BindingResult result){
		if(caravanFactory.findById(caravana.getId())==null){
			return false;			
		}else{
			result.rejectValue("Id", "caravanaAlreadyExists");
			return true;
		}
	}
	@Transactional
	public Caravan addCaravana(Caravan caravana){

		 return caravanFactory.save(caravana);
	}
	
	@Transactional
	public List<Caravan> findAllCaravan(){
		List<Caravan> caravanList = caravanFactory.findAll();
		
		return caravanList;
	}
	
	@Transactional
    public Caravan findCaravanById(Integer id){
        Caravan caravan = caravanFactory.findById(id);
        
        return caravan;
    }
    
    @Transactional
    public void saveAllAvailabilities(ArrayList<Availability> availabilities){
        availabilityRepository.save(availabilities);
    }
    
    @Transactional
    public void saveAllPrices(ArrayList<Price> prices){
        priceRepository.save(prices);
    }

		
	}
