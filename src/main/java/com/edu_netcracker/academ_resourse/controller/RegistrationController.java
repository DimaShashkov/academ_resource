package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import com.edu_netcracker.academ_resourse.domain.model.Nsu;
import com.edu_netcracker.academ_resourse.domain.model.Smtu;
import com.edu_netcracker.academ_resourse.parsing.JsoupPars;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class RegistrationController {

    @Autowired
    JsoupPars jsoupPars;
    private final static Logger logger = Logger.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("message", "");
        logger.info("the empty data has been added into model");
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@RequestParam("university") String university, String group, String email, String password, Model model) {
        if(university.equals("ITMO")) {
            User<Itmo> user = new User();
            user.setUniversity(new Itmo(group));
            user.setEmail(email);
            user.setGroup(group);
            user.setPassword(password);
            addSchedule(user);
        }
        else if(university.equals("SMTU")){
            User<Smtu> user = new User();
            user.setUniversity(new Smtu(group));
            user.setEmail(email);
            user.setGroup(group);
            user.setPassword(password);
            addSchedule(user);
        }
        else if(university.equals("NSU")){
            User<Nsu> user = new User();
            user.setUniversity(new Nsu(group));
            user.setEmail(email);
            user.setGroup(group);
            user.setPassword(password);
            addSchedule(user);
        }

        return "redirect:personal_account";
    }

    private void addSchedule(User user) {
        try {
            jsoupPars.addSchedule(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("the data has been added into model");
    }

}
