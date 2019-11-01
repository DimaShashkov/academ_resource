package com.edu_netcracker.academ_resourse.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    private final static Logger logger = Logger.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("message", "");
        logger.info("the empty data has been added into model");
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration() {
        return "redirect:personal_account";
    }

}
