package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;
import java.util.Map;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("message", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user, Model model) {
        if(user.getPassword().equals("") || user.getUsername().equals("")) {
            model.addAttribute("message", "Incorrect data");
            return "registration";
        }

        String userData = String.format("username: %s | pass: %s", user.getUsername(), user.getPassword());
        model.addAttribute("message", "User successfully registered! " + userData);
        return "registration";
    }

}
