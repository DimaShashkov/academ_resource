package com.edu_netcracker.academ_resourse.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "usr_table")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Email
	private String email;
	private String password;
	private boolean active;

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "usr_role_table", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	private String name;
	private String surname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private Group group;

//    private String token;

	public User() {
	}

	public String getGroupName(){
		return group !=null ? group.getName() : "";
	}
	public String getUniversity(){
		return group !=null ? group.getUniversityName() : "";
	}


	public String getRole() {
		Iterator<Role> iterator = roles.iterator();
		String result = iterator.next().toString();

		return result.substring(0, 1) + result.substring(1).toLowerCase();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Group getGroup() {
		if(group == null) {
			group = new Group();
		}
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}


}
