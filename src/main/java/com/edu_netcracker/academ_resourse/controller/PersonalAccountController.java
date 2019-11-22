package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.schedule.factory.MongoGroupFactory;
import com.edu_netcracker.academ_resourse.schedule.logic.JsoupPars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);
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
            logger.error("schedule wasn't added into Mongo: ", e);
        }
        logger.info("the data has been added into model");
    }
}
