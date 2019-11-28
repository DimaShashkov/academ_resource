package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepo extends JpaRepository<Subject, Long> {
    Subject findSubjectByName(String name);
}
