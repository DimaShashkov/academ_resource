package com.edu_netcracker.academ_resourse.repositories;

import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItmoRepository extends MongoRepository<Itmo, String> {
    List<Itmo> findAllByGroup(String group);
}
