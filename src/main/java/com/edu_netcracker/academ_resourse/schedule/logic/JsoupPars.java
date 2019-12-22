package com.edu_netcracker.academ_resourse.schedule.logic;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.schedule.utils.IJsoupConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JsoupPars implements IJsoupConstants {
    private final static Logger logger = LoggerFactory.getLogger(JsoupPars.class);

    private int errorCountWithLikeURL = 0;
    private String errorURL;


    private final Schedule schedule;

    public JsoupPars(Schedule schedule) {
        this.schedule = schedule;
    }

    public void addSchedule(final MongoGroup mongoGroup, final boolean errorThread) throws IOException {


        Document document = null;
        try {
            document = Jsoup.connect(mongoGroup.getUniversity().getUrl())
                    .userAgent(JSOUP_USERAGENT)
                    .referrer(JSOUP_REFERRER)
                    .get();
        } catch (IOException e) {
            if(errorCountWithLikeURL == 0 || !errorURL.equals(mongoGroup.getUniversity().getUrl())) {
                errorCountWithLikeURL++;
                errorURL = mongoGroup.getUniversity().getUrl();
                throw new IOException(ERROR_URL1);
            }
            else if(errorURL.equals(mongoGroup.getUniversity().getUrl())) {
                errorCountWithLikeURL = 0;
                errorURL = "";
                if(!errorThread) {
                    new Thread(() -> {
                        try {
                            Thread.sleep(120000);
                            addSchedule(mongoGroup, true);
                        } catch (IOException | InterruptedException ex) {
                            logger.error("the second schedule addition failed. URL: " + mongoGroup.getUniversity().getUrl()); // first attempt
                            try {
                                Thread.sleep(120000);
                                addSchedule(mongoGroup, true);
                            } catch (InterruptedException | IOException exc) {
                                logger.error("the third schedule addition failed. URL: " + mongoGroup.getUniversity().getUrl()); // second attempt
                            }
                        }
                    }).start();
                }
                throw new IOException(ERROR_URL2);
            }
        }

        Elements elements = document.select(mongoGroup.getUniversity().getQuery());

        if(elements.isEmpty()) {
            throw new IOException(ERROR_URL1);
        }

        schedule.saveSubjects(mongoGroup, elements);

        if(schedule.existGroup(mongoGroup)) {
            logger.info(ALREADY_EXIST);
            return;
        }

        schedule.save(mongoGroup, elements);

    }

}