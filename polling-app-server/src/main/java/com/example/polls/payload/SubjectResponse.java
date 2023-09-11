package com.example.polls.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import java.time.Instant;
import java.util.List;

public class SubjectResponse {

    private String subjectName;

    private String department;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
