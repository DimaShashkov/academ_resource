package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.TaskLvl;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.services.GroupService;
import com.edu_netcracker.academ_resourse.services.TaskLvlService;
import com.edu_netcracker.academ_resourse.services.TasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class TasksController {
    private final static Logger logger = LoggerFactory.getLogger(TasksController.class);
    private final String ADMIN = "<template v-if=\"true\">";
    private final String USER = "<template v-if=\"false\">";

    @Autowired
    private GroupService groupService;
    @Autowired
    private TasksService tasksService;
    @Autowired
    private TaskLvlService taskLvlService;


    @GetMapping("/tasks")
    public String getSchedule(@AuthenticationPrincipal User user, Model model) {

        if(user.getUniversity() == null || user.getGroupName() == null) {
            return "redirect:/profile";
        }

        String isAdmin = new String();
        if(user.getRole().equals("Admin"))
            isAdmin = ADMIN;
        else if(user.getRole().equals("User"))
            isAdmin = USER;


        if(user.getGroup() == null ||
                user.getGroup().getTasks() != null || !user.getGroup().getTasks().isEmpty()) {
            Set<Task> tasks = user.getGroup().getTasks();
            Set<Subject> subjects = user.getGroup().getSubjects();
            List<TaskLvl> taskLvls = taskLvlService.getTaskLvls();

            model.addAttribute("taskLvls", taskLvls);
            model.addAttribute("tasks", tasks);
            model.addAttribute("subjects", subjects);
        }
        model.addAttribute("admin", isAdmin);

        return "tasks";
    }

    @PostMapping("/tasks")
    public String postSchedule(@AuthenticationPrincipal User user, String date,
                               String subject, String taskText, String link,
                               String taskLvl, Model model) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
             date1 = df.parse(date);
        } catch (ParseException e) {
            logger.error("the date is wrong: ", e);
        }
        Task task = new Task();
        task.setDate(date1);
        task.setSubject(subject);
        task.setTask(taskText);
        task.setLink(link);
        tasksService.setTaskLvl(task, taskLvl);
        groupService.addTasks(task, user.getGroup());
        return "redirect:/tasks";
    }
}
