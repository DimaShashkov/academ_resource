package com.edu_netcracker.academ_resourse.services;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DateService {

    int month;
    int year;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    boolean firstSemester;
    private Date before;
    private Date after;
    private final String FOR_JANUARY_BEFORE = "-12-31";
    private final String FOR_JANUARY_AFTER = "-02-01";
    private final String FOR_FEBRUARY_BEFORE = "-01-31";
    private final String FOR_FEBRUARY_AFTER = "-03-01";
    private final String FOR_MARCH_BEFORE = "-02-29";
    private final String FOR_MARCH_AFTER = "-04-01";
    private final String FOR_APRIL_BEFORE = "-03-31";
    private final String FOR_APRIL_AFTER = "-05-01";
    private final String FOR_MAY_BEFORE = "-04-30";
    private final String FOR_MAY_AFTER = "-06-01";
    private final String FOR_JUNE_BEFORE = "-05-31";
    private final String FOR_JUNE_AFTER = "-07-01";
    private final String FOR_JULY_BEFORE = "-06-30";
    private final String FOR_JULY_AFTER = "-08-01";
    private final String FOR_AUGUST_BEFORE = "-07-31";
    private final String FOR_AUGUST_AFTER = "-09-01";
    private final String FOR_SEPTEMBER_BEFORE = "-08-31";
    private final String FOR_SEPTEMBER_AFTER = "-10-01";
    private final String FOR_OCTOBER_BEFORE = "-09-30";
    private final String FOR_OCTOBER_AFTER = "-11-01";
    private final String FOR_NOVEMBER_BEFORE = "-10-31";
    private final String FOR_NOVEMBER_AFTER = "-12-01";
    private final String FOR_DECEMBER_BEFORE = "-11-30";
    private final String FOR_DECEMBER_AFTER = "-01-01";


    public DateService() {
        Calendar calendar = new GregorianCalendar();
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        if (month >= 9)
            firstSemester = true;
    }

    public Date[] getJanuary() throws ParseException {
        if (firstSemester) {
            before = sdf.parse(year + FOR_JANUARY_BEFORE);
            after = sdf.parse((year + 1) + FOR_JANUARY_AFTER);
        } else {
            before = sdf.parse((year - 1) + FOR_JANUARY_BEFORE);
            after = sdf.parse(year + FOR_JANUARY_AFTER);
        }
        Date[] dates = new Date[2];
        dates[0] = before;
        dates[1] = after;
        return dates;
    }
    public Date[] getFebruary() throws ParseException {
        return getDatesSecond(FOR_FEBRUARY_BEFORE, FOR_FEBRUARY_AFTER);
    }
    public Date[] getMarch() throws ParseException {
        return getDatesSecond(FOR_MARCH_BEFORE, FOR_MARCH_AFTER);
    }
    public Date[] getApril() throws ParseException {
        return getDatesSecond(FOR_APRIL_BEFORE, FOR_APRIL_AFTER);
    }
    public Date[] getMay() throws ParseException {
        return getDatesSecond(FOR_MAY_BEFORE, FOR_MAY_AFTER);
    }
    public Date[] getJune() throws ParseException {
        return getDatesSecond(FOR_JUNE_BEFORE, FOR_JUNE_AFTER);
    }
    public Date[] getJuly() throws ParseException {
        return getDatesSecond(FOR_JULY_BEFORE, FOR_JULY_AFTER);
    }
    public Date[] getAugust() throws ParseException {
        return getDatesSecond(FOR_AUGUST_BEFORE, FOR_AUGUST_AFTER);
    }


    public Date[] getSeptember() throws ParseException {
        return getDatesFirst(FOR_SEPTEMBER_BEFORE, FOR_SEPTEMBER_AFTER);
    }
    public Date[] getOctober() throws ParseException {
        return getDatesFirst(FOR_OCTOBER_BEFORE, FOR_OCTOBER_AFTER);
    }
    public Date[] getNovember() throws ParseException {
        return getDatesFirst(FOR_NOVEMBER_BEFORE, FOR_NOVEMBER_AFTER);
    }
    public Date[] getDecember() throws ParseException {
        if (firstSemester) {
            before = sdf.parse(year + FOR_DECEMBER_BEFORE);
            after = sdf.parse((year + 1) + FOR_DECEMBER_AFTER);
        } else {
            before = sdf.parse((year - 1) + FOR_DECEMBER_BEFORE);
            after = sdf.parse(year + FOR_DECEMBER_AFTER);
        }
        Date[] dates = new Date[2];
        dates[0] = before;
        dates[1] = after;
        return dates;
    }

    private Date[] getDatesFirst(String for_december_before, String for_december_after) throws ParseException {
        if (firstSemester) {
            before = sdf.parse(year + for_december_before);
            after = sdf.parse(year + for_december_after);
        } else {
            before = sdf.parse((year - 1) + for_december_before);
            after = sdf.parse((year - 1) + for_december_after);
        }
        Date[] dates = new Date[2];
        dates[0] = before;
        dates[1] = after;
        return dates;
    }
    private Date[] getDatesSecond(String for_august_before, String for_august_after) throws ParseException {
        if (firstSemester) {
            before = sdf.parse((year + 1) + for_august_before);
            after = sdf.parse((year + 1) + for_august_after);
        } else {
            before = sdf.parse(year + for_august_before);
            after = sdf.parse(year + for_august_after);
        }
        Date[] dates = new Date[2];
        dates[0] = before;
        dates[1] = after;
        return dates;
    }

}
