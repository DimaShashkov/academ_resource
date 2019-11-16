package com.edu_netcracker.academ_resourse.schedule.logic;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.schedule.repositories.*;
import com.edu_netcracker.academ_resourse.schedule.universities.*;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class JsoupPars {
    private final static Logger logger = Logger.getLogger(JsoupPars.class);

    @Autowired
    ItmoRepository itmoRepository;
    @Autowired
    SmtuRepository smtuRepository;
    @Autowired
    NsuRepository nsuRepository;

public void addSchedule(final MongoGroup mongoGroup) throws IOException {

    if(mongoGroup.getUniversity() instanceof Itmo) {
        if(itmoRepository.findAllByGroup(mongoGroup.getGroup()).size() != 0) {
            logger.info("this group already exist");
            return;
        }
    }
    else if (mongoGroup.getUniversity() instanceof Smtu) {
        if(smtuRepository.findAllByGroup(mongoGroup.getGroup()).size() != 0) {
            logger.info("this group already exist");
            return;
        }
    }
    else if (mongoGroup.getUniversity() instanceof Nsu) {
        if(nsuRepository.findAllByGroup(mongoGroup.getGroup()).size() != 0) {
            logger.info("this group already exist");
            return;
        }
    }

    Document document = Jsoup.connect(mongoGroup.getUniversity().getUrl())
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get();

    Elements elements = document.select(mongoGroup.getUniversity().getQuery());

    save(mongoGroup, elements);
}

    private void save(final MongoGroup mongoGroup, final Elements elements) {
        if(mongoGroup.getUniversity() instanceof Itmo) {
            StringBuilder sb = new StringBuilder(elements.toString().replaceAll("border=\"0\"", "border=\"1\""));
            mongoGroup.getUniversity().setSchedule(sb.toString());

            Itmo itmo = (Itmo)mongoGroup.getUniversity();
            itmoRepository.save(itmo);
        }
        else if(mongoGroup.getUniversity() instanceof Smtu) {
            boolean a = false;
            String[] str = elements.toString().split("\n");
            StringBuilder sb = new StringBuilder();
            for (String s : str) {
                if(s.contains("<table cellpadding=\"5\" cellspacing=\"5\" border=\"1\" width=\"100%\">")) {
                    a = true;
                }
                if(s.contains("</table>")) {
                    a = false;
                }
                if(a) {
                    sb.append(s + "\n");
                }
            }
            mongoGroup.getUniversity().setSchedule(sb.toString());
            Smtu smtu = (Smtu) mongoGroup.getUniversity();
            smtuRepository.save(smtu);
        }
        else if(mongoGroup.getUniversity() instanceof Nsu) {
            StringBuilder sb = new StringBuilder();
            String[] str = elements.toString().split("\n");
            for (int i = 0; i < str.length; i++) {
                if (i == 0) {
                    sb.append(str[0].substring(0, str[0].length() - 2) + " border=\"1\"> \n");
                } else {
                    sb.append(str[i] + "\n");
                }
            }
                mongoGroup.getUniversity().setSchedule(sb.toString());

                Nsu nsu = (Nsu) mongoGroup.getUniversity();
                nsuRepository.save(nsu);
            }
    }
}
