package com.edu_netcracker.academ_resourse.domain.universities;

import com.edu_netcracker.academ_resourse.domain.model.University;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Nsu implements University {
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
        String start = "<table cellspacing=\"0\" border=\"1\" class=\"time-table\"> \r\n";
        String end = "</table>";

        DateFormat df = new SimpleDateFormat("EEEE", new Locale("Ru", "ru"));
        char[] charArray = df.format(new Date(new Date().getTime() + 86400000)).toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        String tomorrowDay = new String(charArray);

        int dayNumber = 0;
        if (tomorrowDay.equals("Понедельник"))
            dayNumber = 2;
        if (tomorrowDay.equals("Вторник"))
            dayNumber = 3;
        if (tomorrowDay.equals("Среда"))
            dayNumber = 4;
        if (tomorrowDay.equals("Четверг"))
            dayNumber = 5;
        if (tomorrowDay.equals("Пятница"))
            dayNumber = 6;
        if (tomorrowDay.equals("Суббота"))
            dayNumber = 7;
        if(tomorrowDay.equals("Воскресенье")) {
            dayNumber = 2;
            tomorrowDay = "Понедельник";
        }



        String[] str = schedule.split("<tr>");
        StringBuilder tomorrow = new StringBuilder();
        tomorrow.append(start).append("<tr> \r\n <th>Время</th> \r\n").append("<th>").append(tomorrowDay).append("</th> </tr> \r\n");

        for (int i = 2; i < str.length; i++) {
            String s = str[i];
            String[] strg = s.split("<td>");
            tomorrow.append("<tr> \r\n <td>").append(strg[1]).append("<td>").append(strg[dayNumber]).append("</tr> \r\n");
        }
        tomorrow.append("</table>");
        return tomorrow.toString();
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

