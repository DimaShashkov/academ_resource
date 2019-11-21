package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.IUserRepo;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller

public class UserController {
    @Autowired
	JsoupPars jsoupPars;

    @Autowired
	private IUserRepo userRepo;
	@Autowired
	private IGroupRepo groupRepo;

	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;


	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/profile")
	public String getProfile(
			@AuthenticationPrincipal User user,
			Model model) {

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
			HttpServletResponse response,
			Model model) {

		user.setEmail(email);
		user.setPassword(password);
		user.setUniversity(university);

		groupService.addUserGroup(user, group);

		model.addAttribute("user", user);

		MongoGroup mongoGroup = MongoGroupFactory.getGroup(university, group);
		addSchedule(mongoGroup);
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8080/schedule?univ=").append(university).append("&group=").append(group.toUpperCase());
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "profile";
	}

	private void addSchedule(MongoGroup mongoGroup) {
		try {
			jsoupPars.addSchedule(mongoGroup);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("schedule wasn't added into Mongo: ", e);
		}
		logger.info("the data has been added into model");
	}
}
