package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

	private final UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("message", "");
        logger.info("the empty data has been added into model");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
    	if (!userService.addUser(user)) {
			model.addAttribute("message", "User exists!");
			return "registration";
		}
		return "redirect:/profile";
    }

}
