package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.ISubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private ISubjectRepo subjectRepo;
    @Autowired
    private IGroupRepo groupRepo;

    private List<Subject> subjects;

    public List<Subject> addSubject(String[] sub){
        if(subjects == null)
            subjects = new ArrayList<>();
        for (String s : sub ){
            if (subjectRepo.findSubjectByName(s) != null){
                subjects.add(subjectRepo.findSubjectByName(s));
            } else {
                Subject subject = new Subject(s);
                subjectRepo.save(subject);
                subjects.add(subject);
            }
        }
        return subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}