package com.edu_netcracker.academ_resourse.schedule.universities;

import com.edu_netcracker.academ_resourse.schedule.model.University;
import com.edu_netcracker.academ_resourse.schedule.utils.ISmtuConstans;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Document
public class Smtu extends University implements ISmtuConstans {
    @Field
    private String group;
    @Field
    private String schedule;


    public Smtu(){
    }

    public Smtu(final String group) {
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
        DateFormat df = new SimpleDateFormat("EEEE", new Locale("Ru", "ru"));
        String today = df.format(new Date());
        StringBuilder tomorrow = new StringBuilder();
        String[] strings = schedule.split(THEAD);

        boolean a = false;
        for(String s : strings) {
            if(a) {
                tomorrow.append(SCHEDULE_START).append(END_LINE).append(THEAD).append(s).append(END_TABLE);
                a = false;
            }
            if(s.toLowerCase().contains(today)) {
                a = true;
            }
        }
        if (tomorrow.length() == 0) {
            tomorrow.append(SCHEDULE_START).append(END_LINE).append(THEAD).append(strings[2]).append(END_TABLE);
        }

        return tomorrow.toString();
    }

    @Override
    public String getUrl() {
        return URL + group.toUpperCase() + "/";
    }

    @Override
    public String getQuery() {
        return QUERY;
    }
}
