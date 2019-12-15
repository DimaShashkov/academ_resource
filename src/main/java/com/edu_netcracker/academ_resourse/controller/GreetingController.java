package com.edu_netcracker.academ_resourse.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class GreetingController {
	final static Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@GetMapping("/login")
	public String getGreeting(Model model) {
		return "login";
	}
}