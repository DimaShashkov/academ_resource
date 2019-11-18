package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepo extends JpaRepository<Group, Long> {
	Group findGroupByName(String name);
}
