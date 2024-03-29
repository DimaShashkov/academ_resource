package com.edu_netcracker.academ_resourse.schedule.logic;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.schedule.repositories.IItmoRepository;
import com.edu_netcracker.academ_resourse.schedule.repositories.INsuRepository;
import com.edu_netcracker.academ_resourse.schedule.repositories.ISmtuRepository;
import com.edu_netcracker.academ_resourse.schedule.universities.Itmo;
import com.edu_netcracker.academ_resourse.schedule.universities.Nsu;
import com.edu_netcracker.academ_resourse.schedule.universities.Smtu;
import com.edu_netcracker.academ_resourse.schedule.utils.ISheduleHTMLConstants;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@Component
public class Schedule implements ISheduleHTMLConstants {

    private final
    IItmoRepository IItmoRepository;
    private final
    ISmtuRepository ISmtuRepository;
    private final
    INsuRepository INsuRepository;

    public Schedule(IItmoRepository IItmoRepository, ISmtuRepository ISmtuRepository, INsuRepository INsuRepository) {
        this.IItmoRepository = IItmoRepository;
        this.ISmtuRepository = ISmtuRepository;
        this.INsuRepository = INsuRepository;
    }

    public Boolean existGroup(final MongoGroup mongoGroup) {
        if (mongoGroup.getUniversity() instanceof Itmo) {
            return IItmoRepository.findAllByGroup(mongoGroup.getGroup()).size() != 0;
        } else if (mongoGroup.getUniversity() instanceof Smtu) {
            return ISmtuRepository.findAllByGroup(mongoGroup.getGroup()).size() != 0;
        } else if (mongoGroup.getUniversity() instanceof Nsu) {
            return INsuRepository.findAllByGroup(mongoGroup.getGroup()).size() != 0;
        }
        return false;
    }

    public Model getSchedule(final String univ, final String group, final Model model) {
        StringBuilder week = new StringBuilder();
        String tomorrow = "";

        switch (univ) {
            case SMTU:
                List<Smtu> smtus = ISmtuRepository.findAllByGroup(group);
                for (Smtu smtu : smtus) {
                    week.append(smtu.getSchedule());
                    tomorrow = smtu.getTomorrowSchedule();
                }
                break;
            case ITMO:
                List<Itmo> itmos = IItmoRepository.findAllByGroup(group);
                for (Itmo itmo : itmos) {
                    week.append(itmo.getSchedule());
                    tomorrow = itmo.getTomorrowSchedule();
                }
                break;
            case NSU:
                List<Nsu> nsus = INsuRepository.findAllByGroup(group);
                for (Nsu nsu : nsus) {
                    week.append(nsu.getSchedule());
                    tomorrow = nsu.getTomorrowSchedule();
                }
                break;
        }
        model.addAttribute("tomorrow", tomorrow);
        model.addAttribute("schedule", week);
        return model;
    }

    public void save(final MongoGroup mongoGroup, final Elements elements) {
        if (mongoGroup.getUniversity() instanceof Itmo) {
            mongoGroup.getUniversity().setSchedule(elements.toString().replaceAll(ITMO_TABLE_BEFORE, ITMO_TABLE_AFTER));
            Itmo itmo = (Itmo) mongoGroup.getUniversity();
            IItmoRepository.save(itmo);
        } else if (mongoGroup.getUniversity() instanceof Smtu) {
            boolean a = false;
            String[] str = elements.toString().split(END_LINE);
            StringBuilder sb = new StringBuilder();
            for (String s : str) {
                if (s.contains(SMTU_REGEX)) {
                    a = true;
                }
                if (s.contains(END_TABLE)) {
                    a = false;
                }
                if (a) {
                    sb.append(s).append(END_LINE);
                }
            }
            mongoGroup.getUniversity().setSchedule(sb.toString());
            Smtu smtu = (Smtu) mongoGroup.getUniversity();
            ISmtuRepository.save(smtu);
        } else if (mongoGroup.getUniversity() instanceof Nsu) {
            StringBuilder sb = new StringBuilder();
            String[] str = elements.toString().split(END_LINE);
            for (int i = 0; i < str.length; i++) {
                if (i == 0) {
                    sb.append(str[0], 0, str[0].length() - 2).append(NSU_TABLE);
                } else {
                    sb.append(str[i]).append(END_LINE);
                }
            }
            mongoGroup.getUniversity().setSchedule(sb.toString());

            Nsu nsu = (Nsu) mongoGroup.getUniversity();
            INsuRepository.save(nsu);
        }
    }

    public void saveSubjects(final MongoGroup mongoGroup, final Elements elements) throws IOException {
        if (mongoGroup.getUniversity() instanceof Itmo) {
            StringBuilder sb = new StringBuilder();
            for (Element element : elements.select(ITMO_QUERY)) {
                String s = element.text();
                String[] str = s.split(ITMO_SPLIT);
                if (!sb.toString().contains(str[0]))
                    sb.append(str[0]).append(END_LINE);
            }
            String[] subjects = sb.toString().split(END_LINE);
            mongoGroup.setSubjects(subjects);
        } else if (mongoGroup.getUniversity() instanceof Nsu) {
            StringBuilder sb = new StringBuilder();
            for (Element element : elements.select(NSU_QUERY)) {
                if (!sb.toString().contains(element.text()))
                    sb.append(element.text()).append(END_LINE);
            }
            String[] subjects = sb.toString().split(END_LINE);
            mongoGroup.setSubjects(subjects);
        } else if (mongoGroup.getUniversity() instanceof Smtu) {
            StringBuilder sb = new StringBuilder();
            for (Element element : elements.select(SMTU_QUERY)) {
                String[] str = element.toString().split(SMTU_SPLIT1);
                String[] strings = str[3].split(SMTU_SPLIT2);
                if (!sb.toString().contains(strings[0]))
                    sb.append(strings[0]).append(END_LINE);
            }
            String[] subjects = sb.toString().split(END_LINE);
            if(subjects[0].equals(""))
                throw new IOException("Пожалуйста, проверьте номер группы и вуз");
            mongoGroup.setSubjects(subjects);
        }

    }
}
