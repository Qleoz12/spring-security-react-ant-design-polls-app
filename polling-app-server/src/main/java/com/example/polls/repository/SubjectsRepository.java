package com.example.polls.repository;

import com.example.polls.model.Poll;
import com.example.polls.model.classroom.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Long> {

}
