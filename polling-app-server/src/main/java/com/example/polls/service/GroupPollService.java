package com.example.polls.service;

import com.example.polls.model.PollGroup;
import com.example.polls.payload.PollGroupResponse;
import com.example.polls.payload.PollGroupSimpleRequest;
import com.example.polls.payload.PollResponse;
import com.example.polls.repository.PollGroupRepository;
import com.example.polls.security.UserPrincipal;
import com.example.polls.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupPollService {


    @Autowired
    private PollGroupRepository pollGroupRepository;

    public List<PollGroupResponse> getPollByGroups(UserPrincipal currentUser) {
        List<PollGroup> polls = pollGroupRepository.findAll();

        List<PollGroupResponse> pollResponses = polls.stream().map(poll -> {
            return ModelMapper.toResponse(poll);
        }).collect(Collectors.toList());

        return pollResponses;
    }

    public PollGroupResponse create(PollGroupSimpleRequest pollGroupSimpleRequest,UserPrincipal currentUser) {
        PollGroup pollGroup = new PollGroup();
        pollGroup.setName(pollGroupSimpleRequest.getName());
        pollGroupRepository.save(pollGroup);

        return ModelMapper.toResponse(pollGroup);
    }

}
