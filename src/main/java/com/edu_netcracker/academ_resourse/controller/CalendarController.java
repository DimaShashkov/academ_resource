package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String getController(@AuthenticationPrincipal User user, Model model) {
        Set<Task> tasks = user.getGroup().getTasks();
        model.addAttribute("tasks", tasks);
        return "calendar";
    }
}
