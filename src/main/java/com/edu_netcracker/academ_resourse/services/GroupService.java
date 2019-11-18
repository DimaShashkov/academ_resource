package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	@Autowired
	private IGroupRepo groupRepo;

	public void addGroup(Group group) {
		Group groupFromDB = groupRepo.findGroupByName(group.getName());

		if (groupFromDB == null) {
			groupRepo.save(group);
		}
	}
}
