package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.domain.model.UserFactory;
import com.edu_netcracker.academ_resourse.parsing.JsoupPars;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PersonalAccountController {
    @Autowired
    JsoupPars jsoupPars;

    private final static Logger logger = Logger.getLogger(RegistrationController.class);
    @GetMapping("/personal_account")
    public String getSchedule() {
        return "personal_account";
    }

    @PostMapping("/personal_account")
    public String postSchedule(@RequestParam("university") String university, String group, Model model, HttpServletResponse response) {
        String email = "some@mail.ru";
        String password = "somePass";
        User user = UserFactory.getUser(university, group, email, password);
        addSchedule(user);
        StringBuilder sb = new StringBuilder();
        sb.append("http://localhost:8080/schedule?univ=").append(university).append("&group=").append(group.toUpperCase());
        try {
            response.sendRedirect(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "personal_account";
    }

    private void addSchedule(User user) {
        try {
            jsoupPars.addSchedule(user);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("schedule wasn't added into Mongo");
        }
        logger.info("the data has been added into model");
    }
}
