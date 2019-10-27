package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import com.edu_netcracker.academ_resourse.domain.model.Smtu;
import com.edu_netcracker.academ_resourse.domain.model.University;
import com.edu_netcracker.academ_resourse.repositories.ItmoRepository;
import com.edu_netcracker.academ_resourse.repositories.SmtuRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ItmoRepository itmoRepository;
    @Autowired
    SmtuRepository smtuRepository;
    private final static Logger logger = Logger.getLogger(RegistrationController.class);

    @GetMapping("/schedule")
    public String getSchedule(@RequestParam(value = "univ", defaultValue = "") String univ, Model model) {
        StringBuilder sb = new StringBuilder();
        if(univ.equals("SMTU")) {
            List<Smtu> smtus = smtuRepository.findAllByGroup("1410");
            for (Smtu smtu : smtus) {
                sb.append(smtu.getSchedule());
            }
        }
        else if(univ.equals("ITMO")) {
            List<Itmo> itmos = itmoRepository.findAllByGroup("D3110");
            for (Itmo itmo: itmos) {
                sb.append(itmo.getSchedule());
            }
        }

        model.addAttribute("schedule", sb);
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
