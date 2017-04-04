package com.holinvan.web.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.holinvan.web.model.Availability;
import com.holinvan.web.model.Caravan;
import com.holinvan.web.model.Price;
import com.holinvan.web.service.CampingService;
import com.holinvan.web.service.Caravanaservice;
import com.holinvan.web.validator.CaravanaValidator;

@Controller
@RequestMapping(value = "/caravana")

public class CaravanController {

	private final static String PREFIX = "caravan/";
	private final static String LIST = PREFIX + "caravanList";
	private final static String CARAVAN = PREFIX + "gestionCaravanas";
	private final static String CALENDAR = PREFIX + "calendar";
	private final static String PRICE = PREFIX + "price";
	private final static String SUCCESS = PREFIX + "success";
	
	@Autowired
	Caravanaservice caravanaservice;


	@Autowired
	CaravanaValidator caravanaValidator;
	
	@Autowired
	CampingService campingService;

	@GetMapping("/add")
	public String addCaravana(Model model) {

		model.addAttribute("caravana", new Caravan());
		
		

		
		model.addAttribute("campings", campingService.getAllCampings());

		return CARAVAN;
	}


	@PostMapping("/add")
	public String addCaravana(@Valid @ModelAttribute("caravana") Caravan caravana, Errors errors, BindingResult result, Model model){
		caravanaValidator.validate(caravana, errors);


		if (!result.hasErrors()){	

			caravanaservice.addCaravana(caravana);			


			model.addAttribute("caravanList", caravanaservice.findAllCaravan());

			model.addAttribute("caravana", caravana);

			return SUCCESS;
		}
		System.out.println("ERRORS: " + result.getFieldErrors());
		model.addAttribute("campings", campingService.getAllCampings());
		return CARAVAN;
	}
	
	@GetMapping("/lista")
    public String lista(Model model){
        model.addAttribute("caravanList", caravanaservice.findAllCaravan());
        return LIST;
    }

    @GetMapping("/calendar/{id}")
    public String calendar(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("availabilities", caravanaservice.findCaravanById(id).getAvailabilities());
        model.addAttribute("availability", new Availability());
        
        return CALENDAR;
    }
    
    @PostMapping("/calendar/{id}")
    public String calendar(@PathVariable("id") Integer id, @ModelAttribute("availability") Availability availability, @ModelAttribute("availabilities") ArrayList<Availability> availabilities){
        
        availability.setCaravan(caravanaservice.findCaravanById(id));
        availability.setId(0);
        availabilities.add(availability);
        caravanaservice.saveAllAvailabilities(availabilities);
        
        return CALENDAR;
    }
    
    @GetMapping("/price/{id}")
    public String price(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("prices", caravanaservice.findCaravanById(id).getPrices());
        model.addAttribute("price", new Price());
        
        return PRICE;
    }
    
    @PostMapping("/price/{id}")
    public String price(@PathVariable("id") Integer id, @ModelAttribute("price") Price price, @ModelAttribute("prices") ArrayList<Price> prices){
        
        price.setCaravan(caravanaservice.findCaravanById(id));
        price.setId(0);
        prices.add(price);
        caravanaservice.saveAllPrices(prices);
        
        return PRICE;
    }
	

}
