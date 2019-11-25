package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.University;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import com.edu_netcracker.academ_resourse.repos.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {
	@Autowired
	private IGroupRepo groupRepo;
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private UniversityService universityService;
	@Autowired
	private TasksService tasksService;

	public void addGroup(final Group group) {
		Group groupFromDB = groupRepo.findGroupByName(group.getName());

		if (groupFromDB == null) {
			groupRepo.save(group);
		}
	}


	public Group addGroupUniversity(final String group, final String universityName, final List<Subject> sub){
		University university = universityService.addUniversity(universityName);
		Group groupFromDb = groupRepo.findGroupByName(group);
		if(groupFromDb != null){
			groupFromDb.setUniversity(university);
			groupFromDb.setSubjects(sub);
			return groupFromDb;
		} else {
			Group groupUniv = new Group(group);
			groupUniv.setUniversity(university);
			groupUniv.setSubjects(sub);
			groupRepo.save(groupUniv);
			return groupUniv;
		}
	}

	public Task addTasks(final Task task, final Group group) {
		Task taskFromBD = tasksService.addTasks(task);

		group.addTask(taskFromBD);
		groupRepo.save(group);
		return task;
	}
}
