package com.example.polls.controller;

import com.example.polls.model.Poll;
import com.example.polls.payload.*;
import com.example.polls.provider.IAResponse;
import com.example.polls.repository.PollRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.VoteRepository;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.GroupPollService;
import com.example.polls.service.PollService;
import com.example.polls.util.AppConstants;
import com.example.polls.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/group")
public class GroupPollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private GroupPollService groupPollService;

    private static final Logger logger = LoggerFactory.getLogger(GroupPollController.class);

    @GetMapping()
    public List<PollGroupResponse> get(@CurrentUser UserPrincipal currentUser) {
        return groupPollService.getPollByGroups(currentUser);
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> create(@Valid @RequestBody PollGroupSimpleRequest request,@CurrentUser UserPrincipal currentUser) {

        return ResponseEntity.status(HttpStatus.CREATED).body(groupPollService.create(request,currentUser));
    }
}
