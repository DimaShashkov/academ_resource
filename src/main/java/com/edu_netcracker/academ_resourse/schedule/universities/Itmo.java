package com.edu_netcracker.academ_resourse.schedule.universities;

import com.edu_netcracker.academ_resourse.schedule.model.University;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Document
public class Itmo implements University {
    private String group;
    private String schedule;

    public Itmo() {
    }

    public Itmo(String group) {
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
        DateFormat df = new SimpleDateFormat("E", new Locale("Ru", "ru"));
        String today = df.format(new Date());
        String tomorrowDay = df.format(new Date(new Date().getTime() + 86400000));

        int i = 2;
        while(!schedule.contains(tomorrowDay)) {
            tomorrowDay = df.format(new Date(new Date().getTime()+(86400000 * i)));
            i++;
        }

        StringBuilder tomorrow = new StringBuilder();
        String[] strings = schedule.split("<table");

        for(String s : strings) {
            if(s.contains(tomorrowDay)) {
                tomorrow.append("<table").append(s);
            }
        }
        return tomorrow.toString();
    }

    @Override
    public String getUrl() {
        return "http://www.ifmo.ru/ru/schedule/0/" + group.toUpperCase() + "/raspisanie_zanyatiy_" + group.toUpperCase() + ".htm";
    }

    @Override
    public String getQuery() {
        return "div.rasp_tabl_day";
    }
}
