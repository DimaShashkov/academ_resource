package com.edu_netcracker.academ_resourse.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "task_table")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String task;
    private String subject;
    private Date date;
    private boolean isDone;
    private String link;

//    @ElementCollection(targetClass = TaskLvl.class, fetch = FetchType.EAGER)

//    @JoinTable (name="task_lvl_table",
//            joinColumns=@JoinColumn (name="taskLvl_id", insertable=false, updatable=false),
//            inverseJoinColumns=@JoinColumn(name="task_id", insertable=false, updatable=false))
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="task_lvl_id")
    private TaskLvl taskLvl;


    public Task() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TaskLvl getTaskLvl() {
        return taskLvl;
    }

    public void setTaskLvl(TaskLvl taskLvl) {
        this.taskLvl = taskLvl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return id == task1.id &&
                isDone == task1.isDone &&
                Objects.equals(task, task1.task) &&
                Objects.equals(subject, task1.subject) &&
                Objects.equals(date, task1.date) &&
                Objects.equals(link, task1.link) &&
                Objects.equals(taskLvl, task1.taskLvl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, subject, date, isDone, link, taskLvl);
    }
}
