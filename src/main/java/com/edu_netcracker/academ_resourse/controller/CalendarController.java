package com.edu_netcracker.academ_resourse.controller;

import com.edu_netcracker.academ_resourse.domain.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String getController(Model model) {
        String calendarTable = Calendar.getCalendarTable();
        model.addAttribute("calendar_table", calendarTable);
        return "calendar";
    }
}
