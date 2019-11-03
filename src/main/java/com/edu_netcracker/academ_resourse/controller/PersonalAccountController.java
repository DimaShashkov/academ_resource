package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.schedule.MongoGroup;
import com.edu_netcracker.academ_resourse.schedule.model.MongoGroupFactory;
import com.edu_netcracker.academ_resourse.schedule.parsing.JsoupPars;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PersonalAccountController {
    @Autowired
    JsoupPars jsoupPars;

    private final static Logger logger = Logger.getLogger(PersonalAccountController.class);
    @GetMapping("/personal_account")
    public String getPersonalAccount() {
        return "personal_account";
    }

    @PostMapping("/personal_account")
    public String postPersonalAccount(@RequestParam("university") String university, String group, Model model, HttpServletResponse response) {
        MongoGroup mongoGroup = MongoGroupFactory.getGroup(university, group);
        addSchedule(mongoGroup);
        StringBuilder sb = new StringBuilder();
        sb.append("http://localhost:8080/schedule?univ=").append(university).append("&group=").append(group.toUpperCase());
        try {
            response.sendRedirect(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "personal_account";
    }

    private void addSchedule(MongoGroup mongoGroup) {
        try {
            jsoupPars.addSchedule(mongoGroup);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("schedule wasn't added into Mongo");
        }
        logger.info("the data has been added into model");
    }
}
