package com.edu_netcracker.academ_resourse.repositories;

import com.edu_netcracker.academ_resourse.domain.model.Itmo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Itmo, String> {
    List<Itmo> findAllByGroup(String group);


}
