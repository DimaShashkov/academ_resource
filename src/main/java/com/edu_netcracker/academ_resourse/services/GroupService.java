package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	@Autowired
	private IGroupRepo groupRepo;
	@Autowired
	private IUserRepo userRepo;

	public void addGroup(Group group) {
		Group groupFromDB = groupRepo.findGroupByName(group.getName());

		if (groupFromDB == null) {
			groupRepo.save(group);
		}
	}
	public void addUserGroup(User user, String group) {
		Group groupFromDb = groupRepo.findGroupByName(group);
		if(groupFromDb != null){
			user.setGroup(groupFromDb);
		} else {
			Group userGroup = new Group(group);
			groupRepo.save(userGroup);
			user.setGroup(userGroup);
		}
		userRepo.save(user);
	}
}
