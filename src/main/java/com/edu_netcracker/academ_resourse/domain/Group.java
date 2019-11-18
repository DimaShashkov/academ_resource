package com.edu_netcracker.academ_resourse.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grp")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//	@JoinColumn(name ="group_fk)
//	private Set<User> users;

    public Group(){

	}

	public Group(String name) {
		this.name = name;
	}
	//    private int universityId;
//    private int taskId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String groupName) {
		this.name = name;
	}

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
//
//	public void setUser(User user) {
//		this.users.add(user);
//	}


	//	public int getUniversityId() {
//		return universityId;
//	}
//
//	public void setUniversityId(int universityId) {
//		this.universityId = universityId;
//	}

//	public int getTaskId() {
//		return taskId;
//	}
//
//	public void setTaskId(int taskId) {
//		this.taskId = taskId;
//	}
}
