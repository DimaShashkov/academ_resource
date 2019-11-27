package com.edu_netcracker.academ_resourse.domain;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
	@Table(name = "grp")
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
			cascade = {
					CascadeType.MERGE,
					CascadeType.REFRESH
			})
	@JoinTable (name="grp_subject",
			joinColumns=@JoinColumn (name="grp_id"),
			inverseJoinColumns=@JoinColumn(name="subject_id"))

//	@LazyCollection(LazyCollectionOption.FALSE)
//	@ElementCollection(targetClass=Subject.class)
	private Set<Subject> subjects;

//  private int taskId;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//	@JoinColumn(name ="group_fk)
//	private Set<User> users;

		@Embeddable
		public static class GroupSubject implements Serializable {
			Integer groupID;
			Integer subjectId;

			public GroupSubject() {}

			public GroupSubject(Integer groupID, Integer subjectId) {
				this.groupID = groupID;
				this.subjectId = subjectId;
			}

			@Override
			public boolean equals(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
				GroupSubject that = (GroupSubject) o;
				return Objects.equals(groupID, that.groupID) &&
						Objects.equals(subjectId, that.subjectId);
			}

			@Override
			public int hashCode() {
				return Objects.hash(groupID, subjectId);
			}
		}


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
