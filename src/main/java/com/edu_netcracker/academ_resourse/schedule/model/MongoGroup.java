package com.edu_netcracker.academ_resourse.schedule.model;

public class MongoGroup<T extends University>{
    private T university;
    private String group;
    private String[] subjects;

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

    public String[] getSubjects() {
        if (subjects == null)
            return null;
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }
}
