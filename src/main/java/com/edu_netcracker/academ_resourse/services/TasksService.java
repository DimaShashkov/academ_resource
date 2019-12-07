package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.TaskLvl;
import com.edu_netcracker.academ_resourse.repos.ITasksRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class TasksService {

    final
    ITasksRepo tasksRepo;

    final
    TaskLvlService taskLvlService;

    final
    DateService dateService;

    final static Logger logger = LoggerFactory.getLogger(TasksService.class);

    public TasksService(ITasksRepo tasksRepo, TaskLvlService taskLvlService, DateService dateService) {
        this.tasksRepo = tasksRepo;
        this.taskLvlService = taskLvlService;
        this.dateService = dateService;
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

    public Set<Task> getJanuaryTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getJanuary();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }


    public Set<Task> getFebruaryTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getFebruary();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getMarchTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getMarch();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getAprilTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getApril();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getMayTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getMay();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getJuneTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getJune();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getJulyTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getJuly();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getAugustTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getAugust();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getSeptemberTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getSeptember();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getOctoberTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getOctober();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getNovemberTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getNovember();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }
    public Set<Task> getDecemberTasks(Set<Task> tasks) {
        Date[] dates = new Date[2];
        try {
            dates = dateService.getDecember();
        } catch (ParseException e) {
            logger.error("error parse date");
        }
        return getTasks(tasks, dates);
    }

    private Set<Task> getTasks(Set<Task> tasks, Date[] dates) {
        HashSet<Task> result = new HashSet<>();
        for(Task task : tasks) {
            if(task.getDate().getTime() > dates[0].getTime() && task.getDate().getTime() < dates[1].getTime()) {
                result.add(task);
            }
        }
        return result;
    }

}
