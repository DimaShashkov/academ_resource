package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import com.edu_netcracker.academ_resourse.repositories.ScheduleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;
    private final static Logger logger = Logger.getLogger(RegistrationController.class);

    @GetMapping("/schedule")
    public String getSchedule(Model model) {
        List<Itmo> itmos = scheduleRepository.findAllByGroup("K3400");
        StringBuilder sb = new StringBuilder();
        for(Itmo itmo : itmos) {
            sb.append(itmo.getSchedule());
        }

        model.addAttribute("schedule", sb);
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
