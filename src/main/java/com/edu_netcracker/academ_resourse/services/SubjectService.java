package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.ISubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubjectService {
    @Autowired
    private ISubjectRepo subjectRepo;




    public Set<Subject> addSubject(String[] sub){
        HashSet<Subject> subjects = new HashSet<>();

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
}