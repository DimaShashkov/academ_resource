package com.edu_netcracker.academ_resourse.services;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Role;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.repos.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private GroupService groupService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepo.findUserByEmail(email);
	}


	public boolean addUser(User user) {
		User userFromDb = userRepo.findUserByEmail(user.getEmail());

		if (userFromDb != null) {
			return false;
		}

		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		userRepo.save(user);

		return true;
	}
	public void addGroup(User user, String groupName, String universityName){
		Group group = groupService.addGroupUniversity(groupName, universityName);
		user.setGroup(group);
		userRepo.save(user);
	}

}
