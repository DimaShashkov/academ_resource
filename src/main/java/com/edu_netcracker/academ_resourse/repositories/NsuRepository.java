package com.edu_netcracker.academ_resourse.repositories;

import com.edu_netcracker.academ_resourse.domain.model.Nsu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NsuRepository extends MongoRepository<Nsu, String> {
    List<Nsu> findAllByGroup(String group);
}
