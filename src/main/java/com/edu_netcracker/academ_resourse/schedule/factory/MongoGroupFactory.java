package com.edu_netcracker.academ_resourse.schedule.factory;

import com.edu_netcracker.academ_resourse.schedule.model.MongoGroup;
import com.edu_netcracker.academ_resourse.schedule.universities.Itmo;
import com.edu_netcracker.academ_resourse.schedule.universities.Nsu;
import com.edu_netcracker.academ_resourse.schedule.universities.Smtu;

public class MongoGroupFactory {

    public static MongoGroup getGroup(final String university, final String group) {
        if(university.equals("ITMO")) {
            MongoGroup<Itmo> mongoGroup = new MongoGroup();
            mongoGroup.setUniversity(new Itmo(group));
            mongoGroup.setGroup(group);
            return mongoGroup;
        }
        else if(university.equals("SMTU")){
            MongoGroup<Smtu> mongoGroup = new MongoGroup();
            mongoGroup.setUniversity(new Smtu(group));
            mongoGroup.setGroup(group);
            return mongoGroup;
        }
        else if(university.equals("NSU")){
            MongoGroup<Nsu> mongoGroup = new MongoGroup();
            mongoGroup.setUniversity(new Nsu(group));
            mongoGroup.setGroup(group);
            return mongoGroup;
        }
        return new MongoGroup();
    }
}
