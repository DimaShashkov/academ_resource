package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.services.TasksService;
import com.google.gson.Gson;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Set;

@Controller
public class CalendarController {

    final TasksService tasksService;

    public CalendarController(TasksService tasksService, Gson gson) {
        this.tasksService = tasksService;
    }

    @GetMapping(value = "/calendar")
    public String getController() {
        return "calendar";
    }

    @GetMapping("/calendar/tasks")
    @ResponseBody
    public Set<Task> getTasksJson(@AuthenticationPrincipal User user) {
        return user.getGroup().getTasks();
    }
}
