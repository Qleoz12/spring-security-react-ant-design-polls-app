package com.example.polls.service;

import com.example.polls.exception.ResourceNotFoundException;
import com.example.polls.model.*;
import com.example.polls.model.classroom.Subject;
import com.example.polls.model.classroom.SubjectPollProfessor;
import com.example.polls.model.classroom.Teacher;
import com.example.polls.payload.*;
import com.example.polls.repository.PollGroupRepository;
import com.example.polls.repository.SubjectPollProfessorRepository;
import com.example.polls.repository.SubjectsRepository;
import com.example.polls.security.UserPrincipal;
import com.example.polls.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassRoomService {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Autowired
    private PollGroupRepository pollGroupRepository;

    @Autowired
    private SubjectPollProfessorRepository subjectPollProfessorRepository;


    @Autowired
    ApiClient apiClient;

    private static final Logger logger = LoggerFactory.getLogger(ClassRoomService.class);

    public PagedResponse<Subject> getAll(UserPrincipal currentUser, int page, int size) {
//        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Subject> subjects =subjectsRepository.findAll(pageable);

        if (subjects.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), subjects.getNumber(),
                    subjects.getSize(), subjects.getTotalElements(), subjects.getTotalPages(), subjects.isLast());
        }

        // Map Polls to PollResponses containing vote counts and poll creator details
        List<Long> pollIds = subjects.map(Subject::getSubjectId).getContent();


        return new PagedResponse<Subject>(subjects.getContent(), page,
                subjects.getSize(),subjects.getTotalElements(), subjects.getTotalPages(), subjects.isLast());
    }



    public Subject create(SubjectRequest request) {
        Subject subject = new Subject();
        subject.setSubjectName(request.getName());
        subject.setDepartment(request.getDepartment());

        return subjectsRepository.save(subject);
    }

    public SubjectPollProfessorResponse asociate(UserPrincipal currentUser, SubjectPollProfessorResponse request) {
        SubjectPollProfessor subjectPollProfessor= new SubjectPollProfessor();

        Subject subject=subjectsRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("subject", "id", request));

        PollGroup pollGroup=pollGroupRepository.findById(request.getPollGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("pollGroup", "id", request));


        User teacher= new User();
        teacher.setId(currentUser.getId());

        subjectPollProfessor.setSubject(subject);
        subjectPollProfessor.setPollGroup(pollGroup);
        subjectPollProfessor.setTeacher(teacher);

        return ModelMapper.toResponse(subjectPollProfessorRepository.save(subjectPollProfessor));
    }


    public SubjectResponse getById(Long id, UserPrincipal currentUser){

        Subject subject = subjectsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("subject", "id", id));
        return ModelMapper.toResponse(subject);

    }


}
