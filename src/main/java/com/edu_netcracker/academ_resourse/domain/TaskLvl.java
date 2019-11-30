package com.edu_netcracker.academ_resourse.domain;

public enum TaskLvl {
    TEST("Test"),
    COURSEWORK("Coursework"),
    RESEARCH("Research"),
    REPORT("Report"),
    ABSTRACT("Abstract");

    private final String type;

    private TaskLvl(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
