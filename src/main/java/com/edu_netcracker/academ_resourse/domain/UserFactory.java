package com.edu_netcracker.academ_resourse.domain;

import com.edu_netcracker.academ_resourse.domain.universities.Itmo;
import com.edu_netcracker.academ_resourse.domain.universities.Nsu;
import com.edu_netcracker.academ_resourse.domain.universities.Smtu;

public class UserFactory {

    public static User getUser(String university, String group, String email, String password) {
        if(university.equals("ITMO")) {
            User<Itmo> user = new User();
            user.setUniversity(new Itmo(group));
            user.setEmail(email);
            user.setGroup(group);
            user.setPassword(password);
            return user;
        }
        else if(university.equals("SMTU")){
            User<Smtu> user = new User();
            user.setUniversity(new Smtu(group));
            user.setEmail(email);
            user.setGroup(group);
            user.setPassword(password);
            return user;
        }
        else if(university.equals("NSU")){
            User<Nsu> user = new User();
            user.setUniversity(new Nsu(group));
            user.setEmail(email);
            user.setGroup(group);
            user.setPassword(password);
            return user;
        }
        return new User();
    }
}
