package com.edu_netcracker.academ_resourse.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public String getTomorrowSchedule() {
        String start = "<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\" width=\"100%\">";
        DateFormat df = new SimpleDateFormat("EEEE", new Locale("Ru", "ru"));
        String today = df.format(new Date());
        StringBuilder tomorrow = new StringBuilder();
        String[] strings = schedule.split("<thead>");

        boolean a = false;
        for(String s : strings) {
            if(a) {
                tomorrow.append(start + "\r\n" + "<thead>" + s + "</table>");
                a = false;
            }
            if(s.toLowerCase().contains(today)) {
                a = true;
            }

        }
        return tomorrow.toString();
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
