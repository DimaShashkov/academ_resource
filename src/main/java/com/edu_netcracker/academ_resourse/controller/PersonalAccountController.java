package com.edu_netcracker.academ_resourse.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonalAccountController {

    private final static Logger logger = Logger.getLogger(RegistrationController.class);
    @GetMapping("/personal_account")
    public String getSchedule() {
        return "personal_account";
    }

    @PostMapping("/personal_account")
    public String postSchedule() {
        return "personal_account";
    }
}
