package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.schedule.logic.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ScheduleController {

    @Autowired
    Schedule schedule;

    private final static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @GetMapping("/schedule")
    public String getSchedule(@AuthenticationPrincipal User user, Model model) {
        if(user.getUniversity() == null || user.getGroupName() == null) {
            return "redirect:/profile";
        }
        model = schedule.getSchedule(user.getUniversity(), user.getGroupName(), model);
        logger.info("return user schedule for university: " + user.getUniversity() + " group: " + user.getGroupName());
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
