package com.example.polls.model.classroom;

import com.example.polls.model.Poll;

import javax.persistence.*;

@Entity
@Table(name = "subject_poll_professor")
public class SubjectPollProfessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_poll_professor_id")
    private Long subjectPollProfessorId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Long getSubjectPollProfessorId() {
        return subjectPollProfessorId;
    }

    public void setSubjectPollProfessorId(Long subjectPollProfessorId) {
        this.subjectPollProfessorId = subjectPollProfessorId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
