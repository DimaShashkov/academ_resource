package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.TaskLvl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskLvlRepo extends JpaRepository<TaskLvl, Long> {
    TaskLvl findAllByName(String name);
}
