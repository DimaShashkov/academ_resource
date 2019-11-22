package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.University;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.IUniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {
	@Autowired
	private IUniversityRepo universityRepo;
	@Autowired
	private IGroupRepo groupRepo;

//	public void addUniversity(String university, Group group){
//		University universityFromDB = universityRepo.findUniversityByName(university);
//		if(universityFromDB != null){
//			group.setUniversity(universityFromDB);
//		} else {
//			University groupUniversity = new University(university);
//			universityRepo.save(groupUniversity);
//			group.setUniversity(groupUniversity);
//		}
//		groupRepo.save(group);
//	}
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
