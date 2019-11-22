package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.schedule.logic.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ScheduleController {

    @Autowired
    Schedule schedule;

    private final static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @GetMapping("/schedule")
    public String getSchedule(@RequestParam(value = "univ", defaultValue = "") String univ,
                              @RequestParam(value = "group", defaultValue = "") String group, Model model) {
        model = schedule.getSchedule(univ, group, model);
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
