package com.edu_netcracker.academ_resourse.domain;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects_table")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToMany(mappedBy = "subjects")
//    @JoinColumn(name = "group_id")
    private Set<Group> groups;

//	@ManyToMany
//	@JoinColumn(name = "grop_id")
//	private Group group;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public long getId() {
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


    public Set<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        if(groups == null)
            groups = new HashSet<>();
        groups.add(group);
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}