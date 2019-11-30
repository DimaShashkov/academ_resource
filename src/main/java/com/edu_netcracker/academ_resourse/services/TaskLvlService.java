package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.TaskLvl;
import com.edu_netcracker.academ_resourse.repos.ITaskLvlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskLvlService {

    @Autowired
    ITaskLvlRepo taskLvlRepo;

    private static final List<String> taskLvl;
    private List<TaskLvl> taskLvls;

    static {
        taskLvl = new ArrayList<>();
        taskLvl.add("Test");
        taskLvl.add("Coursework");
        taskLvl.add("Research");
        taskLvl.add("Report");
        taskLvl.add("Abstract");
    }

    public List<TaskLvl> getTaskLvl() {
        return taskLvls;
    }

    public TaskLvl addTaskLvl(TaskLvl taskLvl) {
        if(taskLvls == null)
            taskLvls = new ArrayList<>();
        TaskLvl taskLvl1FromBD = taskLvlRepo.findAllByName(taskLvl.getName());
        if(taskLvl1FromBD == null) {
            taskLvlRepo.save(taskLvl);
            taskLvls.add(taskLvl);
            return taskLvl;
        }
        else {
            return taskLvl1FromBD;
        }
    }

    public void setTaskLvlTable() {
        for(String s : taskLvl) {
            addTaskLvl(new TaskLvl(s));
        }
    }
}
