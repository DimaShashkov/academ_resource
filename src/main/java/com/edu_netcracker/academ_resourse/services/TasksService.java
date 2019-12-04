package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.TaskLvl;
import com.edu_netcracker.academ_resourse.repos.ITasksRepo;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    final ITasksRepo tasksRepo;

    final TaskLvlService taskLvlService;

	public TasksService(ITasksRepo tasksRepo, TaskLvlService taskLvlService) {
		this.tasksRepo = tasksRepo;
		this.taskLvlService = taskLvlService;
	}


	public Task addTasks(final Task task) {
        Task taskFromBD = tasksRepo.findTasksByTaskAndDate(task.getTask(), task.getDate());

        if (taskFromBD != null) {
            return taskFromBD;
        } else{
            tasksRepo.save(task);
            return task;
        }
    }

    public void setTaskLvl(Task task, String taskLvl) {
        task.setTaskLvl(taskLvlService.addTaskLvl(new TaskLvl(taskLvl)));

        tasksRepo.saveAndFlush(task);
    }

}
