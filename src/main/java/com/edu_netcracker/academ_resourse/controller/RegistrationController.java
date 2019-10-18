package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;

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
    public String postRegistration(User user, Model model) {
        if(user.getPassword().equals("") || user.getUsername().equals("")) {
            model.addAttribute("message", "Incorrect data");
            logger.info("incorrect data registration has been added into model");
            return "registration";
        }

        String userData = String.format("username: %s | pass: %s", user.getUsername(), user.getPassword());
        model.addAttribute("message", "User successfully registered! " + userData);
        logger.info("the data has been added into model");
        return "registration";
    }

}
