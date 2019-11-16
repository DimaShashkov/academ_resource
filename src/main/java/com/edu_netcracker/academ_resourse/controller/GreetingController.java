package com.edu_netcracker.academ_resourse.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class GreetingController {
	final static Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@GetMapping("/main")
	public String getGreeting(@RequestParam(name="name", required=false, defaultValue="default") String name,
						   Model model) {
		if(name.equals("default")) {
			name = "Soon there will be the Academ Resource!";
			logger.info("the name variable has been initialized is empty data");
		}
		model.addAttribute("name", name);

		return "main";
	}

	@PostMapping("/main")
	public String postGreeting(@RequestParam(name="name", required=false, defaultValue="default") String name,
							   Model model) {
		if(name.equals("default")) {
			name = "Soon there will be the Academ Resource!";
			logger.info("the name variable has been initialized is empty data");
		}
		model.addAttribute("name", name);

		return "main";
	}

}