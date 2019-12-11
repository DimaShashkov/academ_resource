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
    public String getController(@AuthenticationPrincipal User user, Model model) {
        Set<Task> tasks = user.getGroup().getTasks();
        Set<Task> januaryTask = tasksService.getJanuaryTasks(tasks);
        Set<Task> februaryTask = tasksService.getFebruaryTasks(tasks);
        Set<Task> marchTask = tasksService.getMarchTasks(tasks);
        Set<Task> aprilTask = tasksService.getAprilTasks(tasks);
        Set<Task> mayTask = tasksService.getMayTasks(tasks);
        Set<Task> juneTask = tasksService.getJuneTasks(tasks);
        Set<Task> julyTask = tasksService.getJulyTasks(tasks);
        Set<Task> augustTask = tasksService.getAugustTasks(tasks);
        Set<Task> septemberTask = tasksService.getSeptemberTasks(tasks);
        Set<Task> octoberTask = tasksService.getOctoberTasks(tasks);
        Set<Task> novemberTask = tasksService.getNovemberTasks(tasks);
        Set<Task> decemberTask = tasksService.getDecemberTasks(tasks);

        model.addAttribute("januaryTask", januaryTask);
        model.addAttribute("februaryTask", februaryTask);
        model.addAttribute("marchTask", marchTask);
        model.addAttribute("aprilTask", aprilTask);
        model.addAttribute("mayTask", mayTask);
        model.addAttribute("juneTask", juneTask);
        model.addAttribute("julyTask", julyTask);
        model.addAttribute("augustTask", augustTask);
        model.addAttribute("septemberTask", septemberTask);
        model.addAttribute("octoberTask", octoberTask);
        model.addAttribute("novemberTask", novemberTask);
        model.addAttribute("decemberTask", decemberTask);
        return "calendar";
    }

    @GetMapping("/calendar/tasks")
    @ResponseBody
    public Set<Task> getTasksJson(@AuthenticationPrincipal User user) {
        return user.getGroup().getTasks();
    }
}
