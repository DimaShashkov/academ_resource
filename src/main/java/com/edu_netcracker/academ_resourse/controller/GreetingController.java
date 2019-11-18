package com.edu_netcracker.academ_resourse.controller;



import com.edu_netcracker.academ_resourse.repos.IUserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class GreetingController {
	final static Logger logger = Logger.getLogger(GreetingController.class);

	@Autowired
	private IUserRepo IUserRepo;
	@GetMapping("/")
	public String getGreeting(Model model) {
		return "greeting";
	}
//

	@GetMapping("/main")
	public String main(Model model) {
		return "profile";
	}

	@PostMapping("/main")
	public String add(Model model) {

		return "profile";
	}

}