package com.holinvan.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.holinvan.web.model.Camping;
import com.holinvan.web.service.CampingService;
import com.holinvan.web.type.Response;
import com.holinvan.web.type.ResponseStatus;

@Controller
public class CampingController {
	
	@Autowired
	CampingService campingService;
	
	@GetMapping("/listCampings")
	public String getCampings(Model model) {
		model.addAttribute("campingList", campingService.getAllCampings());
	    return "camping/campingList";
	  }
	  
	@GetMapping("/addCamping")
	public String Camping(Model model) {
		model.addAttribute("camping", new Camping());
	    return "camping/addCamping";
	  }
	
	@PostMapping("/addCamping")
	public String addCamping(@Valid @ModelAttribute("camping") Camping camping, BindingResult result, Model model) {
		
		Response response = new Response(ResponseStatus.OK, "formAddCamping.okMsg");
		if (result.hasErrors()){
			response.setMessageCode("formAddCamping.failMsg");
			response.setStatus(ResponseStatus.ERROR);
			System.out.println(result.getFieldErrors());
		}
		else {
			campingService.addNewCamping(camping);
		    return "camping/camping-register-success";
		}
		model.addAttribute("addCampingResponse", response);
		return "camping/addCamping";
				
	  }

}
