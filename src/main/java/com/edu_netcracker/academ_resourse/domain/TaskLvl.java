package com.edu_netcracker.academ_resourse.domain;

import javax.persistence.*;

@Entity
@Table(name = "task_lvl_table")
public class TaskLvl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public TaskLvl(String name) {
        this.name = name;
    }

    public TaskLvl() {
    }

    public String getName() {
        return name;
    }
}
