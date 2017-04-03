package com.holinvan.web.controller;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.holinvan.web.model.User;
import com.holinvan.web.service.EmailService;
import com.holinvan.web.service.UserDataService;
import com.holinvan.web.service.UserService;
import com.holinvan.web.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserDataService userDataService;

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;

	public static final String VIEW_PEOPLE = "persons";
	public static final String REGISTER_PAGE = "user/register";
	public static final String REGISTRATION_OK = "user/registration-success";
	public static final String REGISTRATION_FAIL = "user/registration-error";
	public static final String ACTIVATION_OK = "user/activation-success";
	public static final String ACTIVATION_FAIL = "user/activation-error";
	public static final String EDIT_USER = "info";
	public static final String REDIRECT_EDIT_USER = "redirect:/" +EDIT_USER;
	public static final String PASS_RECOVERY = "pass-recovery";
	
	@GetMapping("/register")
	public String regsiter(Model model) {
		model.addAttribute("user", new User());
	    return "user/register";
	}

	@PostMapping("/register")
	public String registro(@Valid @ModelAttribute("user") User user, BindingResult result, Locale locale, HttpServletRequest request){
		userValidator.validate(user, result);
		if (result.hasErrors()){
			return REGISTER_PAGE;
		}
		if (userService.userAlreadyExists(user, result)){
			return REGISTER_PAGE;
		}else {
			userService.addNewUser(user);
			try {
				emailService.sendConfirmationMail(userService.findUserByUsername(user.getUsername()), locale, getURLWithContextPath(request));
				return REGISTRATION_OK;
			} catch (MessagingException e) {
				e.printStackTrace();
				return REGISTRATION_FAIL;
			}
		}		
	}

	@GetMapping("/hello/{code}")
	public String enableUser(@PathVariable String code){
		if (userService.findBySecurityCodeAndSetEnabled(code)){
			return	ACTIVATION_OK;
		}else{
			return	ACTIVATION_FAIL;
		}
	}
	
	@GetMapping("/passwordRecovery")
	public String passRecoveryPage(@AuthenticationPrincipal User activeUser, Model model){
		model.addAttribute("pass1", new String());
		return PASS_RECOVERY;
	}

	@PostMapping("/passwordRecovery")
	public String passRecovery(@Valid @ModelAttribute("pass1") String pass1, @ModelAttribute("pass2") String pass2, BindingResult result,@AuthenticationPrincipal User activeUser, Locale locale){
		if (pass1 != pass2){
			return PASS_RECOVERY;
		}else
			activeUser.setPassword(pass1);
			try {
				emailService.sendChangedPassMail(activeUser.getUserData().getEmail(), locale);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		return "hello";
	}
	
	public static String getURLWithContextPath(HttpServletRequest request) {
		   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}

}