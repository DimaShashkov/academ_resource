package com.edu_netcracker.academ_resourse.schedule.logic;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class JsoupPars {
    private final static Logger logger = Logger.getLogger(JsoupPars.class);

    private final String JSOUP_USERAGENT = "Chrome/4.0.249.0 Safari/532.5";
    private final String JSOUP_REFERRER = "http://www.google.com";
    private final String ALREADY_EXIST = "this group already exist";


    @Autowired
    Schedule schedule;

public void addSchedule(final MongoGroup mongoGroup) throws IOException {

    if(schedule.existGroup(mongoGroup)) {
        logger.info(ALREADY_EXIST);
        return;
    }

    Document document = Jsoup.connect(mongoGroup.getUniversity().getUrl())
            .userAgent(JSOUP_USERAGENT)
            .referrer(JSOUP_REFERRER)
            .get();

    Elements elements = document.select(mongoGroup.getUniversity().getQuery());

    schedule.save(mongoGroup, elements);
}

}
