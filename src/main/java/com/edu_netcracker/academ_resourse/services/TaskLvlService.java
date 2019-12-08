package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.TaskLvl;
import com.edu_netcracker.academ_resourse.repos.ITaskLvlRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskLvlService {

    final
    ITaskLvlRepo taskLvlRepo;

    private static final List<String> taskLvl;
    private Set<TaskLvl> taskLvls;

    static {
        taskLvl = new ArrayList<>();
        taskLvl.add("Test");
        taskLvl.add("Coursework");
        taskLvl.add("Research");
        taskLvl.add("Report");
        taskLvl.add("Abstract");
    }

    public TaskLvlService(ITaskLvlRepo taskLvlRepo) {
        this.taskLvlRepo = taskLvlRepo;
    }

    public Set<TaskLvl> getTaskLvl() {
        return taskLvls;
    }

    public TaskLvl addTaskLvl(TaskLvl taskLvl) {
        if(taskLvls == null)
            taskLvls = new HashSet<>();
        TaskLvl taskLvl1FromBD = taskLvlRepo.findAllByName(taskLvl.getName());
        if(taskLvl1FromBD == null) {
            taskLvlRepo.save(taskLvl);
//            taskLvls.add(taskLvl);
            return taskLvl;
        }
        else {
            taskLvls.add(taskLvl);
            return taskLvl1FromBD;
        }
    }

    public void setTaskLvlTable() {
        for(String s : taskLvl) {
            addTaskLvl(new TaskLvl(s));
        }
    }

    public List<TaskLvl> getTaskLvls() {
        return taskLvlRepo.findAll();
    }
}
