package com.edu_netcracker.academ_resourse.parsing;

import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import com.edu_netcracker.academ_resourse.repositories.ScheduleRepository;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;


public class JsoupPars {
private final String ITMO_URL = "http://www.ifmo.ru/ru/schedule/0/";
private final String UNECON_URL = "https://rasp.unecon.ru/raspisanie_grp.php?g=";
private final String ITMO_QUERY = "div.rasp_tabl_day";
private final String UNECON_QUERY = "iv.rasp";

    private final static Logger logger = Logger.getLogger(JsoupPars.class);

    @Autowired
    ScheduleRepository scheduleRepository;

public void addSchedule(String group) throws IOException {

    if(scheduleRepository.findAllByGroup(group).size() != 0) {
        logger.info("this group already exist");
        return;
    }

        String url = String.format("%s%s%s%s.htm", ITMO_URL, group.toUpperCase(), "/raspisanie_zanyatiy_", group.toUpperCase());

    Document document = Jsoup.connect(url)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get();

    Elements elements = document.select(ITMO_QUERY);


    StringBuilder sb = new StringBuilder(elements.toString().replaceAll("border=\"0\"", "border=\"1\""));
    Itmo itmo = new Itmo(group);
    itmo.setSchedule(sb.toString());
    scheduleRepository.save(itmo);
}


}
