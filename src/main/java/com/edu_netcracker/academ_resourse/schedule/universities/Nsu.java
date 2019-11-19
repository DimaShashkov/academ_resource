package com.edu_netcracker.academ_resourse.schedule.universities;

import com.edu_netcracker.academ_resourse.schedule.model.University;
import org.springframework.data.mongodb.core.mapping.Field;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Nsu extends University {
    @Field
    private String group;
    @Field
    private String schedule;
    private static String URL = "https://table.nsu.ru/group/";
    private static String SCHEDULE_START = "<table cellspacing=\"0\" border=\"1\" class=\"time-table\"> \r\n";
    private static String QUERY = "table.time-table";
    private static String TABLE = "<table>";
    private static String TR = "<tr>";
    private static String TH = "<th>";
    private static String TD = "<td>";
    private static String TOMORROW_1 = "<tr> \r\n <th>Время</th> \r\n";
    private static String TOMORROW_2 = "</th> </tr> \r\n";
    private static String TOMORROW_3 = "<tr> \r\n <td>";
    private static String TOMORROW_4 = "</tr> \r\n";

    public Nsu() {
    }

    public Nsu(final String group) {
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

        final DateFormat df = new SimpleDateFormat("EEEE", new Locale("Ru", "ru"));
        final char[] charArray = df.format(new Date(new Date().getTime() + 86400000)).toCharArray();
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


        String[] str = schedule.split(TR);
        StringBuilder tomorrow = new StringBuilder();
        tomorrow.append(SCHEDULE_START).append(TOMORROW_1).append(TH).append(tomorrowDay).append(TOMORROW_2);

        for (int i = 2; i < str.length; i++) {
            String s = str[i];
            String[] strg = s.split(TD);
            tomorrow.append(TOMORROW_3).append(strg[1]).append(TD).append(strg[dayNumber]).append(TOMORROW_4);
        }
        tomorrow.append(TABLE);
        return tomorrow.toString();
    }

    @Override
    public String getUrl() {
        return URL + group.toUpperCase();
    }

    @Override
    public String getQuery() {
        return QUERY;
    }
}

