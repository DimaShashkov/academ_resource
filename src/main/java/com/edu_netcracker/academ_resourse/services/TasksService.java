package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.repos.ITasksRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class TasksService {

    @Autowired
    ITasksRepo tasksRepo;

    public Task addTasks(Task task) {
        Task taskFromBD = tasksRepo.findTasksByTaskAndDate(task.getTask(), task.getDate());

        if (taskFromBD != null) {
            return taskFromBD;
        } else{
            tasksRepo.save(task);
            return task;
        }
    }
}