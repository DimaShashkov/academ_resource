package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.schedule.factory.MongoGroupFactory;
import com.edu_netcracker.academ_resourse.schedule.logic.JsoupPars;
import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.services.GroupService;
import com.edu_netcracker.academ_resourse.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller

public class UserController {
    @Autowired
	JsoupPars jsoupPars;

	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;


	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/profile")
	public String getProfile(
			@AuthenticationPrincipal User user,
			Model model) {
		String role = user.getRole();
		model.addAttribute("user", user);

		return "profile";
	}
	@PostMapping("/profile")
	public String updateUser(
			@AuthenticationPrincipal User user,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String group,
			@RequestParam String university,
			@RequestParam String role,
			Model model) {

		user.setEmail(email);
		user.setPassword(password);

		userService.addGroup(user, group, university);
		userService.addRole(user, role);
		logger.info("role: ", user.getRole());

		model.addAttribute("user", user);

		MongoGroup mongoGroup = MongoGroupFactory.getGroup(university, group);
		addSchedule(mongoGroup);


		return "redirect:/schedule";
	}

	private void addSchedule(MongoGroup mongoGroup) {
		try {
			jsoupPars.addSchedule(mongoGroup);
		} catch (IOException e) {
			logger.error("schedule wasn't added into Mongo: ", e);
		}
		logger.info("the data has been added into model");
	}
}
