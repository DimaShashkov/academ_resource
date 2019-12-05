package com.edu_netcracker.academ_resourse.services;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Role;
import com.edu_netcracker.academ_resourse.domain.Subject;
import com.edu_netcracker.academ_resourse.domain.User;
import com.edu_netcracker.academ_resourse.repos.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private GroupService groupService;
	@Autowired
	private PasswordEncoder passwordEncoder;


//	public UserService(IUserRepo userRepo, GroupService groupService) {
//		this.userRepo = userRepo;
//		this.groupService = groupService;
//	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepo.findUserByEmail(email);
	}


	public boolean addUser(User user) {
		User userFromDb = userRepo.findUserByEmail(user.getEmail());

		user.setRoles(Collections.singleton(Role.USER));


		if (userFromDb != null) {
			return false;
		}

		user.setActive(true);

		user.setRoles(Collections.singleton(Role.USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepo.save(user);

		return true;
	}
	public void addGroup(User user, String groupName, String universityName, Set<Subject> sub){
		Group group = groupService.addGroupUniversity(groupName, universityName, sub);
		user.setGroup(group);
		userRepo.save(user);
	}

	public void addRole(User user, String role) {
		if(role.equals("USER")) {
			user.setRoles(Collections.singleton(Role.USER));
		}
		if(role.equals("ADMIN")) {
			user.setRoles(Collections.singleton(Role.ADMIN));
		}
		userRepo.save(user);
	}

}