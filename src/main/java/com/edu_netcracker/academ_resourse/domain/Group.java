package com.edu_netcracker.academ_resourse.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grp")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="univ_id")
	private University university;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="group_id")
	private List<Task> tasks;

//  private int taskId;

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

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public List<Task> getTasks() {
    	if(tasks == null) {
    		tasks = new ArrayList<>();
		}
		return tasks;
	}

	public void addTask(Task task) {
    	if(tasks.isEmpty())
    		tasks = new ArrayList<>();
		this.tasks.add(task);
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getUniversityName(){
		return university !=null ? university.getName() : "<none>";
	}
}
