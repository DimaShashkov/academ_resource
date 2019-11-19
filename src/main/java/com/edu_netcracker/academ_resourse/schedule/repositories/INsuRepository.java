package com.edu_netcracker.academ_resourse.schedule.repositories;

import com.edu_netcracker.academ_resourse.schedule.universities.Nsu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INsuRepository extends MongoRepository<Nsu, String> {
    List<Nsu> findAllByGroup(String group);
}
