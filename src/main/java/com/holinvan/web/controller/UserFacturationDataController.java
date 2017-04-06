package com.holinvan.web.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.holinvan.web.model.User;
import com.holinvan.web.model.UserFacturationData;
import com.holinvan.web.service.EmailService;
import com.holinvan.web.service.UserFacturationDataService;
import com.holinvan.web.service.UserService;
import com.holinvan.web.type.Response;
import com.holinvan.web.type.ResponseStatus;

@Controller
public class UserFacturationDataController {
	
	private static Logger LOG = LoggerFactory.getLogger(UserFacturationDataController.class);
	public static final String HOME_PAGE = "hello";
	public static final String USER_FACTURATION = "user/facturation-data";
	
	@Autowired
	UserFacturationDataService userFacturationDataService;

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;
	
	@GetMapping("/facturation-data")
	public String redirectToFacturationUser(@AuthenticationPrincipal User activeUser, Model model){
		UserFacturationData userFacturationData = new UserFacturationData();
		if (activeUser.getUserFacturationData() != null) {
			userFacturationData = activeUser.getUserFacturationData();
			LOG.info(userFacturationData.toString());
		}
		model.addAttribute("facturation_data", userFacturationData);
		return USER_FACTURATION;
	}

	@PostMapping("/facturation-data")
	public String editFacturationUserInfo(@Valid @ModelAttribute("facturation_data") UserFacturationData userFacturationData, BindingResult result, 
			@AuthenticationPrincipal User activeUser, Locale locale, Model model){
		
		Response response = new Response(ResponseStatus.OK, "formBilling.okMsg");
		if (result.hasErrors()){
			response.setMessageCode("formBilling.failMsg");
			response.setStatus(ResponseStatus.ERROR);
			return USER_FACTURATION;
		}
		else {
			userFacturationDataService.editFacturationUserInfo(activeUser, userFacturationData);
		}
		model.addAttribute("facturationDataResponse", response);
		return USER_FACTURATION;		
	}
}