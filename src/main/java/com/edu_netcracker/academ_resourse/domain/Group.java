package com.edu_netcracker.academ_resourse.domain;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.ParseException;
import java.util.*;


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
	private Set<Task> tasks;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
	public Set<Task> getTasks() {
		if(tasks == null) {
			tasks = new HashSet<>();
		}
		return tasks;
	}

	public void addTask(Task task) {
		if(tasks.isEmpty())
			tasks = new HashSet<>();
		this.tasks.add(task);
	}

	public void setTasks(Set<Task> tasks) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Group group = (Group) o;
		return id == group.id &&
				Objects.equals(name, group.name) &&
				Objects.equals(university, group.university) &&
				Objects.equals(tasks, group.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, university, tasks);
	}

}
