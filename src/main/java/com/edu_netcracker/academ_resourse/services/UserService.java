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
	private IUserRepo IUserRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return IUserRepo.findUserByEmail(email);
	}


	public boolean addUser(User user) {
		User userFromDb = IUserRepo.findUserByEmail(user.getEmail());

		if (userFromDb != null) {
			return false;
		}

		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		IUserRepo.save(user);

		return true;
	}

}
