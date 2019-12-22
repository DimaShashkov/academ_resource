package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.services.TasksService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CalendarController {

    private final TasksService tasksService;

    public CalendarController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping(value = "/calendar")
    public String getController() {
        return "calendar";
    }

    @GetMapping("/calendar/tasks")
    @ResponseBody
    public List<Task> getTasksJson(@AuthenticationPrincipal User user) {
        return user.getGroup().getTasksList();
    }
}
