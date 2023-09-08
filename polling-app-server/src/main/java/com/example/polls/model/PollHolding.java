package com.example.polls.model;

import com.example.polls.model.classroom.Student;

import javax.persistence.*;

@Entity
@Table(name = "polls_holding")
public class PollHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_holding_id")
    private Long pollHoldingId;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getPollHoldingId() {
        return pollHoldingId;
    }

    public void setPollHoldingId(Long pollHoldingId) {
        this.pollHoldingId = pollHoldingId;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
