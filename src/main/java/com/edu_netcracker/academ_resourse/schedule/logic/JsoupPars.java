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
    private final String ERROR_URL1 = "Пожалуйста, проверьте номер группы и вуз";
    private final String ERROR_URL2 = "Пожалуйста, подождите. Сайт университета не отвечает," +
            " повторная попытка добавления расписания произойдет автоматически, приносим извенения за неудобства.";
    private int errorCountWithLikeURL = 0;
    private String errorURL;


    private final Schedule schedule;

    public JsoupPars(Schedule schedule) {
        this.schedule = schedule;
    }

    public void addSchedule(final MongoGroup mongoGroup) throws IOException {


        Document document = null;
        try {
            document = Jsoup.connect(mongoGroup.getUniversity().getUrl())
                    .userAgent(JSOUP_USERAGENT)
                    .referrer(JSOUP_REFERRER)
                    .get();
        } catch (IOException e) {
            if(errorCountWithLikeURL == 0) {
                errorCountWithLikeURL++;
                errorURL = mongoGroup.getUniversity().getUrl();
                throw new IOException(ERROR_URL1);
            }
            else if(errorURL.equals(mongoGroup.getUniversity().getUrl())) {
                errorCountWithLikeURL = 0;
                errorURL = "";
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        addSchedule(mongoGroup);
                    } catch (IOException | InterruptedException ex) {
                        logger.error("the second schedule addition failed. URL: " + mongoGroup.getUniversity().getUrl()); // first attempt
                        try {
                            Thread.sleep(2000);
                            addSchedule(mongoGroup);
                        } catch (InterruptedException | IOException exc) {
                            logger.error("the third schedule addition failed. URL: " + mongoGroup.getUniversity().getUrl()); // second attempt
                        }
                    }
                }).start();
                throw new IOException(ERROR_URL2);
            }
        }

        Elements elements = document.select(mongoGroup.getUniversity().getQuery());

        schedule.saveSubjects(mongoGroup, elements);

        if(schedule.existGroup(mongoGroup)) {
            logger.info(ALREADY_EXIST);
            return;
        }

        schedule.save(mongoGroup, elements);

    }

}