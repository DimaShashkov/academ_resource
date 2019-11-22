package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TasksController {
    private final static Logger logger = LoggerFactory.getLogger(TasksController.class);
    @GetMapping("/tasks")
    public String getSchedule(@AuthenticationPrincipal User user, Model model) {
        String isAdmin = new String();
        if(user.getRole().equals("Admin"))
            isAdmin = "<template v-if=\"true\">";
        else if(user.getRole().equals("User"))
            isAdmin = "<template v-if=\"false\">";
        model.addAttribute("admin", isAdmin);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String postSchedule() {
        return "tasks";
    }
}
