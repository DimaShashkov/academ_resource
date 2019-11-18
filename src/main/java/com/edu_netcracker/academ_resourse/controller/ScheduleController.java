package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.schedule.universities.*;
import com.edu_netcracker.academ_resourse.schedule.repositories.*;
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
    @Autowired
    NsuRepository nsuRepository;
    private final static Logger logger = Logger.getLogger(RegistrationController.class);

    @GetMapping("/schedule")
    public String getSchedule(@RequestParam(value = "univ", defaultValue = "") String univ,
                              @RequestParam(value = "group", defaultValue = "") String group, Model model) {
        StringBuilder week = new StringBuilder();
        String tomorrow = new String();
        if(univ.equals("SMTU")) {
            List<Smtu> smtus = smtuRepository.findAllByGroup(group);
            for (Smtu smtu : smtus) {
                week.append(smtu.getSchedule());
                tomorrow = smtu.getTomorrowSchedule();
            }
        }
        else if(univ.equals("ITMO")) {
            List<Itmo> itmos = itmoRepository.findAllByGroup(group);
            for (Itmo itmo: itmos) {
                week.append(itmo.getSchedule());
                tomorrow = itmo.getTomorrowSchedule();
            }
        }
        else if(univ.equals("NSU")) {
            List<Nsu> nsus = nsuRepository.findAllByGroup(group);
            for (Nsu nsu: nsus) {
                week.append(nsu.getSchedule());
                tomorrow = nsu.getTomorrowSchedule();
            }
        }
        model.addAttribute("tomorrow", tomorrow);
        model.addAttribute("schedule", week);
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
