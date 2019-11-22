package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUniversityRepo extends JpaRepository<University, Long> {
	University findUniversityByName(String name);
}
