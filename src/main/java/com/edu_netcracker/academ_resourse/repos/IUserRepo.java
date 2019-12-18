package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.Group;
import com.edu_netcracker.academ_resourse.domain.Role;
import com.edu_netcracker.academ_resourse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepo extends JpaRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserById(long id);
	List<User> findUserByGroup(Group group);
}
