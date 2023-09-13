package com.example.polls.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubjectPollProfessorResponse {

    private Long subjectPollProfessorId;

    @NotNull
    private Long subjectId;

    private Long teacherId;

    @NotNull
    private Long pollGroupId;

    public Long getSubjectPollProfessorId() {
        return subjectPollProfessorId;
    }

    public void setSubjectPollProfessorId(Long subjectPollProfessorId) {
        this.subjectPollProfessorId = subjectPollProfessorId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getPollGroupId() {
        return pollGroupId;
    }

    public void setPollGroupId(Long pollGroupId) {
        this.pollGroupId = pollGroupId;
    }
}
