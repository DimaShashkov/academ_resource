package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.University;
import com.edu_netcracker.academ_resourse.repos.IUniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {
	private final IUniversityRepo universityRepo;

	public UniversityService(IUniversityRepo universityRepo) {
		this.universityRepo = universityRepo;
	}

	public University addUniversity(String universityName) {
		University universityFromDB = universityRepo.findUniversityByName(universityName);

		if (universityFromDB != null) {
			return universityFromDB;
		} else{
			University university = new University(universityName);
			universityRepo.save(university);
			return university;
		}
	}
}
