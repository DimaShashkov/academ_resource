package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ITasksRepo extends JpaRepository<Task, Long> {
    Task findTasksByTaskAndDate(String task, Date date);
}
