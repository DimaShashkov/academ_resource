package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.schedule.factory.MongoGroupFactory;
import com.edu_netcracker.academ_resourse.schedule.logic.JsoupPars;
import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.services.SubjectService;
import com.edu_netcracker.academ_resourse.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Set;

@Controller

public class UserController {
    final JsoupPars jsoupPars;

    private final UserService userService;

    private final SubjectService subjectService;

    private final PasswordEncoder passwordEncoder;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(JsoupPars jsoupPars, UserService userService, SubjectService subjectService, PasswordEncoder passwordEncoder) {
		this.jsoupPars = jsoupPars;
		this.userService = userService;
		this.subjectService = subjectService;
		this.passwordEncoder = passwordEncoder;
	}


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
            @RequestParam String role,
            Model model) {

        user.setEmail(email);
        if(!password.isEmpty()){
			user.setPassword(passwordEncoder.encode(password));
		}

        MongoGroup mongoGroup = MongoGroupFactory.getGroup(university, group);

        userService.addRole(user, role);

        model.addAttribute("user", user);

        addSchedule(mongoGroup);

        Set<Subject> sub = subjectService.addSubject(mongoGroup.getSubjects());
        userService.addGroup(user, group, university, sub);

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
