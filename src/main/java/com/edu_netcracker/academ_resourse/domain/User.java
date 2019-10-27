package com.edu_netcracker.academ_resourse.domain;

import com.edu_netcracker.academ_resourse.domain.model.University;

public class User <T extends University>{
    private String email;
    private String password;
    private T university;
    private String group;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public T getUniversity() {
        return university;
    }

    public void setUniversity(T university) {
        this.university = university;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
