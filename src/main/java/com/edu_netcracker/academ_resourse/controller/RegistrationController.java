package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RegistrationController {

	private final UserService userService;
	private final AuthenticationManager authManager;
	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();



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
    public String addUser(User user, HttpServletRequest request, Model model) {
    	if (!userService.addUser(user)) {
			model.addAttribute("error", "Пользователь с таким E-mail уже зарегистрирован");
			logger.info("this user already exist");
			return "registration";
		}
		login(request, user);
		return "redirect:/profile";
    }

	public void login(HttpServletRequest req, User user) {
		UsernamePasswordAuthenticationToken authReq
				= new UsernamePasswordAuthenticationToken(user.getEmail(), passwordEncoder.encode(user.getPassword()));
		Authentication auth = authManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
	}

}