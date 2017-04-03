package com.holinvan.web.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.holinvan.web.model.UserData;
import com.holinvan.web.service.EmailService;
import com.holinvan.web.service.UserDataService;
import com.holinvan.web.service.UserService;

@Controller
public class UserDataController {

	@Autowired
	UserDataService userDataService;

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	public static final String HOME_PAGE = "hello";
	public static final String EDIT_USER = "user/info";
	
	private static Logger LOG = LoggerFactory.getLogger(UserDataController.class);

	
	@GetMapping("/info")
	public String redirectToEditUser(@AuthenticationPrincipal User activeUser, Model model){
		UserData userData = new UserData();
		if (activeUser.getUserData()!=null){
			userData = activeUser.getUserData();
			LOG.info(userData.toString());
		}
		model.addAttribute("userData", userData);
		return EDIT_USER;
	}

	@PostMapping("/info")
	public String editUserInfo(@Valid @ModelAttribute("userData") UserData userData, BindingResult result, @AuthenticationPrincipal User activeUser, HttpServletRequest request){
		if (result.hasErrors()){
			System.out.println(result.getFieldErrors());
			return EDIT_USER;
		}
		else {
			userDataService.editUserInfo(activeUser, userData);
		}
		return EDIT_USER;		
	}

}