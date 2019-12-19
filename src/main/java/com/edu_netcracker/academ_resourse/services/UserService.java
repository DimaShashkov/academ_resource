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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private GroupService groupService;
	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();



	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		return userRepo.findUserByEmail(email);
	}


	public boolean addUser(final User user) {
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
	public void addGroup(final User user, final String groupName,
						 final String universityName, final Set<Subject> sub){
		Group group = groupService.addGroupUniversity(groupName, universityName, sub);
		user.setGroup(group);
		userRepo.save(user);
	}

	public void addRole(final User user, final String role) {
		if(role.equalsIgnoreCase("USER")) {
			user.setRoles(Collections.singleton(Role.USER));
		}
		if(role.equalsIgnoreCase("ADMIN")) {
			List<User> userList = userRepo.findUserByGroup(user.getGroup());
			boolean containsAdmin = false;
			for(User usr: userList) {
				if (usr.getRole().equals("Admin"))
					containsAdmin = true;
			}
			if(!containsAdmin)
				user.setRoles(Collections.singleton(Role.ADMIN));
			else
				user.setRoles(Collections.singleton(Role.USER));
		}
		userRepo.save(user);
	}
}