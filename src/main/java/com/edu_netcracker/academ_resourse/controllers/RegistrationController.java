package com.edu_netcracker.academ_resourse.controllers;

import com.edu_netcracker.academ_resourse.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String getRegistration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user, Map<String, Object> model) {
        if(user.getPassword().equals("") || user.getUsername().equals("")) {
            model.put("message", "Incorrect data");
            return "registration";
        }

        String userData = String.format("username: %s | pass: %s", user.getUsername(), user.getPassword());
        model.put("message", "User successfully registered! " + userData);
        return "registration";
    }

}
