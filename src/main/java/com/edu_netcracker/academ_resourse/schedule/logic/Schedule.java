package com.edu_netcracker.academ_resourse.schedule.logic;

import com.edu_netcracker.academ_resourse.schedule.repositories.ItmoRepository;
import com.edu_netcracker.academ_resourse.schedule.repositories.NsuRepository;
import com.edu_netcracker.academ_resourse.schedule.repositories.SmtuRepository;
import com.edu_netcracker.academ_resourse.schedule.universities.Itmo;
import com.edu_netcracker.academ_resourse.schedule.universities.Nsu;
import com.edu_netcracker.academ_resourse.schedule.universities.Smtu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class Schedule {
    @Autowired
    ItmoRepository itmoRepository;
    @Autowired
    SmtuRepository smtuRepository;
    @Autowired
    NsuRepository nsuRepository;

    public Model getSchedule(final String univ, final String group, final Model model) {
        StringBuilder week = new StringBuilder();
        String tomorrow = new String();

        if (univ.equals("SMTU")) {
            List<Smtu> smtus = smtuRepository.findAllByGroup(group);
            for (Smtu smtu : smtus) {
                week.append(smtu.getSchedule());
                tomorrow = smtu.getTomorrowSchedule();
            }
        } else if (univ.equals("ITMO")) {
            List<Itmo> itmos = itmoRepository.findAllByGroup(group);
            for (Itmo itmo : itmos) {
                week.append(itmo.getSchedule());
                tomorrow = itmo.getTomorrowSchedule();
            }
        } else if (univ.equals("NSU")) {
            List<Nsu> nsus = nsuRepository.findAllByGroup(group);
            for (Nsu nsu : nsus) {
                week.append(nsu.getSchedule());
                tomorrow = nsu.getTomorrowSchedule();
            }
        }
        model.addAttribute("tomorrow_schedule", tomorrow);
        model.addAttribute("schedule", week);
        return model;
    }
}
