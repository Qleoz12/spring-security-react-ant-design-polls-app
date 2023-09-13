package com.example.polls.repository;

import com.example.polls.model.classroom.Subject;
import com.example.polls.model.classroom.SubjectPollProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectPollProfessorRepository extends JpaRepository<SubjectPollProfessor, Long> {

}
