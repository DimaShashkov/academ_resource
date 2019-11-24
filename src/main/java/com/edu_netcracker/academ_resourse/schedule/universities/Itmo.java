package com.edu_netcracker.academ_resourse.schedule.universities;

import com.edu_netcracker.academ_resourse.schedule.model.University;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Document
public class Itmo extends University {
    @Field
    private String group;
    @Field
    private String schedule;
    private String[] subjects;
    private static String URL1 = "http://www.ifmo.ru/ru/schedule/0/";
    private static String URL2 = "/raspisanie_zanyatiy_";
    private static String URL3 = ".htm";
    private static String QUERY = "div.rasp_tabl_day";
    private static String TABLE = "<table";

    public Itmo() {
    }

    public Itmo(final String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    public void setSchedule(final String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return this.schedule;
    }

    @Override
    public String getTomorrowSchedule() {
        DateFormat df = new SimpleDateFormat("E", new Locale("Ru", "ru"));
        String tomorrowDay = df.format(new Date(new Date().getTime() + 86400000));

        int i = 2;
        while(!schedule.contains(tomorrowDay)) {
            tomorrowDay = df.format(new Date(new Date().getTime()+(86400000 * i)));
            i++;
        }

        StringBuilder tomorrow = new StringBuilder();
        String[] strings = schedule.split(TABLE);

        for(String s : strings) {
            if(s.contains(tomorrowDay)) {
                tomorrow.append(TABLE).append(s);
            }
        }
        return tomorrow.toString();
    }

    @Override
    public String getUrl() {
        return URL1 + group.toUpperCase() + URL2 + group.toUpperCase() + URL3;
    }

    @Override
    public String getQuery() {
        return QUERY;
    }

}
