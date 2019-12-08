package com.edu_netcracker.academ_resourse.schedule.logic;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsoupPars {
    private final static Logger logger = LoggerFactory.getLogger(JsoupPars.class);


    private final String JSOUP_USERAGENT = "Chrome/4.0.249.0 Safari/532.5";
    private final String JSOUP_REFERRER = "http://www.google.com";
    private final String ALREADY_EXIST = "this group already exist";


    private final Schedule schedule;

    public JsoupPars(Schedule schedule) {
        this.schedule = schedule;
    }

    public void addSchedule(final MongoGroup mongoGroup) throws IOException {



        Document document = Jsoup.connect(mongoGroup.getUniversity().getUrl())
                .userAgent(JSOUP_USERAGENT)
                .referrer(JSOUP_REFERRER)
                .get();

        Elements elements = document.select(mongoGroup.getUniversity().getQuery());

        schedule.saveSubjects(mongoGroup, elements);

        if(schedule.existGroup(mongoGroup)) {
            logger.info(ALREADY_EXIST);
            return;
        }

        schedule.save(mongoGroup, elements);

    }

}