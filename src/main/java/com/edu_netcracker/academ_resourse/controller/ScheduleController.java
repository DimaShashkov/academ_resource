package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import com.edu_netcracker.academ_resourse.domain.model.Nsu;
import com.edu_netcracker.academ_resourse.domain.model.Smtu;
import com.edu_netcracker.academ_resourse.repositories.ItmoRepository;
import com.edu_netcracker.academ_resourse.repositories.NsuRepository;
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
    @Autowired
    NsuRepository nsuRepository;
    private final static Logger logger = Logger.getLogger(RegistrationController.class);

    @GetMapping("/schedule")
    public String getSchedule(@RequestParam(value = "univ", defaultValue = "") String univ, Model model) {
        StringBuilder week = new StringBuilder();
        String tomorrow = new String();
        if(univ.equals("SMTU")) {
            List<Smtu> smtus = smtuRepository.findAllByGroup("1410");
            for (Smtu smtu : smtus) {
                week.append(smtu.getSchedule());
                tomorrow = smtu.getTomorrowSchedule();
            }
        }
        else if(univ.equals("ITMO")) {
            List<Itmo> itmos = itmoRepository.findAllByGroup("D3110");
            for (Itmo itmo: itmos) {
                week.append(itmo.getSchedule());
                tomorrow = itmo.getTomorrowSchedule();
            }
        }
        else if(univ.equals("NSU")) {
            List<Nsu> nsus = nsuRepository.findAllByGroup("19161");
            for (Nsu nsu: nsus) {
                week.append(nsu.getSchedule());
            }
        }
        model.addAttribute("tomorrow_schedule", tomorrow);
        model.addAttribute("schedule", week);
        return "schedule";
    }

    @PostMapping("/schedule")
    public String postSchedule() {
        return "schedule";
    }
}
