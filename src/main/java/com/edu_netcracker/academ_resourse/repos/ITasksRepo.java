package com.edu_netcracker.academ_resourse.repos;

import com.edu_netcracker.academ_resourse.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ITasksRepo extends JpaRepository<Task, Long> {
    Task findTasksByTaskAndDate(String task, Date date);
    List<Task> findAllTasksByDateAfterAndDateBefore(Date after, Date before);
}
