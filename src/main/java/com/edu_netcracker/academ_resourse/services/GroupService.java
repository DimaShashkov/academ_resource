package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.University;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.ITasksRepo;
import com.edu_netcracker.academ_resourse.repos.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	@Autowired
	private IGroupRepo groupRepo;
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private UniversityService universityService;
	@Autowired
	ITasksRepo tasksRepo;

	public void addGroup(Group group) {
		Group groupFromDB = groupRepo.findGroupByName(group.getName());

		if (groupFromDB == null) {
			groupRepo.save(group);
		}
	}
//	public void addUserGroup(User user, String group, String university) {
//		universityService.addUniversity(university);
//		Group groupFromDb = groupRepo.findGroupByName(group);
//		if(groupFromDb != null){
//
//			user.setGroup(groupFromDb);
//		} else {
//			Group userGroup = new Group(group);
//			groupRepo.save(userGroup);
//			user.setGroup(userGroup);
//		}
//		userRepo.save(user);
//	}

	public Group addGroupUniversity(String group, String universityName){
		University university = universityService.addUniversity(universityName);
		Group groupFromDb = groupRepo.findGroupByName(group);
		if(groupFromDb != null){
			groupFromDb.setUniversity(university);
			//groupRepo.save(groupFromDb); Возможно здесь это нужно
			return groupFromDb;
		} else {
			Group groupUniv = new Group(group);
			groupUniv.setUniversity(university);
			groupRepo.save(groupUniv);
			return groupUniv;
		}
	}

	public Task addTasks(Task task, Group group) {
		Task taskFromBD = tasksRepo.findTasksByTaskAndDate(task.getTask(), task.getDate());
		if(taskFromBD != null)
			return taskFromBD;

		group.addTask(task);
		tasksRepo.save(task);
		groupRepo.save(group);
			return task;
	}
}
