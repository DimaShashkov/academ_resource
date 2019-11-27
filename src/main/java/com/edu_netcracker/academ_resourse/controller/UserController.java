package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.schedule.factory.MongoGroupFactory;
import com.edu_netcracker.academ_resourse.schedule.logic.JsoupPars;
import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.services.GroupService;
import com.edu_netcracker.academ_resourse.services.SubjectService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller

public class UserController {
    @Autowired
    JsoupPars jsoupPars;

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

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
            @RequestParam String role,
            Model model) {

        user.setEmail(email);
        user.setPassword(password);

        MongoGroup mongoGroup = MongoGroupFactory.getGroup(university, group);

        userService.addRole(user, role);

        model.addAttribute("user", user);

        addSchedule(mongoGroup);

            Set<Subject> sub = subjectService.addSubject(mongoGroup.getSubjects());
            userService.addGroup(user, group, university, sub);

        //////////// В этом месте уже можно доставать через mongoGroup.getSubjects() все предметы для этой
        //////////// группы, вытащенные из расписания на неделю
//		System.out.println(Arrays.toString(mongoGroup.getSubjects()));
//
//		System.out.println(user.getGroup().getSubjects());

        //System.out.println(user.getGroup().);

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
