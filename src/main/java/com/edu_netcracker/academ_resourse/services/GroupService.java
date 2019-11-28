package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.domain.Task;
import com.edu_netcracker.academ_resourse.domain.University;
import com.edu_netcracker.academ_resourse.repos.IGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GroupService {
	@Autowired
	private IGroupRepo groupRepo;
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


	public Group addGroupUniversity(final String group, final String universityName,
									final Set<Subject> sub){
		University university = universityService.addUniversity(universityName);
		Group groupFromDb = groupRepo.findGroupByName(group);
		if(groupFromDb != null){
			groupFromDb.setUniversity(university);


			    groupFromDb.setSubjects(sub);
			    groupRepo.save(groupFromDb);


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
