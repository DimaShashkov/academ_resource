package com.edu_netcracker.academ_resourse.schedule.repositories;

import com.edu_netcracker.academ_resourse.schedule.universities.Itmo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItmoRepository extends MongoRepository<Itmo, String> {
    List<Itmo> findAllByGroup(String group);
}
