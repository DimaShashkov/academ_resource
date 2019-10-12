package com.edu_netcracker.academ_resourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping
	public String greeting(@RequestParam(name="name", required=false, defaultValue="default") String name,
						   Model model) {
		if(name.equals("default")) {
			name = "Soon there will be the Academ Resource!";
		}
		model.addAttribute("name", name);
		return "main";
	}

	@PostMapping
	public String postGreeting(@RequestParam(name="name", required=false, defaultValue="default") String name,
							   Model model) {
		if(name.equals("default")) {
			name = "Soon there will be the Academ Resource!";
		}
		model.addAttribute("name", name);
		return "main";
	}

}