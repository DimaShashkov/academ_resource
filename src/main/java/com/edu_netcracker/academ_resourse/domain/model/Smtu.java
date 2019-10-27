package com.edu_netcracker.academ_resourse.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Smtu implements University{
    private String group;
    private String schedule;

    public Smtu() {
    }

    public Smtu(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return this.schedule;
    }

    @Override
    public String getUrl() {
        return "https://www.smtu.ru/ru/viewschedule/" + group.toUpperCase() + "/";
    }

    @Override
    public String getQuery() {
        return "div.l-container";
    }
}
