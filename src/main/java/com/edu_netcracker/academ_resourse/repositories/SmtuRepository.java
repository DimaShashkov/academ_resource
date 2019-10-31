package com.edu_netcracker.academ_resourse.repositories;

import com.edu_netcracker.academ_resourse.domain.universities.Smtu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmtuRepository extends MongoRepository<Smtu, String> {
    List<Smtu> findAllByGroup(String group);
}