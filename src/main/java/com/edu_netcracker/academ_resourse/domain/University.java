package com.edu_netcracker.academ_resourse.domain;

import javax.persistence.*;

@Entity
@Table(name="university")
public class University {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;



//	private String number;
//
//	private String email;

	public University() {
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

	public void setName(String name) {
		this.name = name;
	}

	public University(String name) {
		this.name = name;
	}
}
