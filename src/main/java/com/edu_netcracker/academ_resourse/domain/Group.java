package com.edu_netcracker.academ_resourse.domain;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


	@Entity
	@Table(name = "grp_table")
	public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="univ_id")
	private University university;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="group_id")
	private List<Task> tasks;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	@JoinTable (name="grp_subject_table",
			joinColumns=@JoinColumn (name="grp_id", insertable=false, updatable=false),
			inverseJoinColumns=@JoinColumn(name="subject_id", insertable=false, updatable=false))
	private Set<Subject> subjects;

	public Group(){

	}

	public Group(String name) {
		this.name = name;
	}


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

	@Transactional
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

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public void addSubject(Subject subject) {
	    if(subjects == null)
	        subjects = new HashSet<>();
	    subjects.add(subject);
    }
}
