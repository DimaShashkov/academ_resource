package com.edu_netcracker.academ_resourse.domain.model;

public class Nsu implements University{
    private String group;
    private String schedule;

    public Nsu() {
    }

    public Nsu(String group) {
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
    public String getTomorrowSchedule() {
        return null;
    }

    @Override
    public String getUrl() {
        return "https://table.nsu.ru/group/" + group.toUpperCase();
    }

    @Override
    public String getQuery() {
        return "table.time-table";
    }
}

