package com.ntt.spring.controller;


import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntt.spring.model.User;
import com.ntt.spring.service.LoginService;
import com.ntt.spring.service.SignupService;

@Controller
public class HomeController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private SignupService signupService;
	
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String home(Locale locale, Model model) {

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) throws SQLException {
		//System.out.println("User Page Requested");
		String userName=user.getUserName();
		String password=user.getPassword();
		System.out.println("^^^^^^^^"+user.getUserName()+"*******"+user.getPassword());
		boolean result= loginService.login(userName, password);
	//	model.addAttribute("userName", userName);
		if(result)
			return "user";
		else	
		return "failure";
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String userSignup(@Validated User user, Model model) throws SQLException {
		System.out.println("SignUp Page Requested");
		System.out.println("hiiiii!!");
		String userName=user.getUserName();
		String password=user.getPassword();
		boolean result= signupService.sign(userName, password);
		//model.addAttribute("userName", userName);
		if(result)
			return "home";
		else	
		return "failure";
	}
	
	
}
