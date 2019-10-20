package com.edu_netcracker.academ_resourse.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScheduleController {
    private final static Logger logger = Logger.getLogger(RegistrationController.class);
    @GetMapping("/schedule")
    public String getSchedule() {
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
