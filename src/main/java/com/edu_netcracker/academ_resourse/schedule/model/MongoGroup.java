package com.edu_netcracker.academ_resourse.schedule.model;

import com.edu_netcracker.academ_resourse.schedule.model.University;

public class MongoGroup<T extends University>{
    private T university;
    private String group;

    public T getUniversity() {
        return university;
    }

    public void setUniversity(T university) {
        this.university = university;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
