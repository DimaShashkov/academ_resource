package com.edu_netcracker.academ_resourse.schedule.repositories;

import com.edu_netcracker.academ_resourse.schedule.universities.Smtu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISmtuRepository extends MongoRepository<Smtu, String> {
    List<Smtu> findAllByGroup(String group);
}