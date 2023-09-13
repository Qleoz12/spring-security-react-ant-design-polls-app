package com.example.polls.repository;

import com.example.polls.model.Poll;
import com.example.polls.model.PollGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollGroupRepository extends JpaRepository<PollGroup, Long> {

    Optional<PollGroup> findById(Long id);

    Page<PollGroup> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

    List<PollGroup> findByIdIn(List<Long> pollIds);

    List<PollGroup> findByIdIn(List<Long> pollIds, Sort sort);
}
