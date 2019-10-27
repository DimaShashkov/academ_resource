package com.edu_netcracker.academ_resourse.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Itmo {
    private String group;
    private String schedule;

    public Itmo(String group) {
        this.group = group;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return this.schedule;
    }
}
