package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserById(long id);
}
