package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

	private final UserService userService;
	private final AuthenticationManager authManager;


    private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	public RegistrationController(UserService userService, AuthenticationManager authManager) {
		this.userService = userService;
		this.authManager = authManager;
	}

	@GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("message", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
    	if (!userService.addUser(user)) {
			model.addAttribute("error", "Пользователь с таким E-mail уже зарегистрирован");
			logger.info("this user already exist");
			return "registration";
		}
		return "redirect:/profile";
    }

}