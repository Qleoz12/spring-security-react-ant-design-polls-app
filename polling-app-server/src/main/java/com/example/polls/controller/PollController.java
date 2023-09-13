package com.example.polls.controller;

import com.example.polls.model.*;
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
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollService pollService;

    @Autowired
    private GroupPollService groupPollService;

    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

    @GetMapping
    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getAllPolls(currentUser, page, size);
    }
//
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
//        Poll poll = pollService.createPoll(pollRequest);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{pollId}")
//                .buildAndExpand(poll.getId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "Poll Created Successfully"));
//    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createPollGroup(@Valid @RequestBody PollGroupRequest request) {
        
        List<Poll> polls =pollService.createPollGroup(request);
        List<PollResponse> pollsResponse=polls.stream().map(ModelMapper::mapPollToPollResponseSimple).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(pollsResponse);
    }

    @GetMapping("/{pollId}")
    public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long pollId) {
        return pollService.getPollById(pollId, currentUser);
    }



    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
                         @PathVariable Long pollId,
                         @Valid @RequestBody VoteRequest voteRequest) {
        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
    }

    @PostMapping("/dinamic")
    @PreAuthorize("hasRole('ADMIN')")
    public IAResponse dinamic(@CurrentUser UserPrincipal currentUser,
                              @Valid @RequestBody PollDinamicRequest request) {
        return pollService.dinamicPoll(request, currentUser);
    }


    @GetMapping("/group/{id}")
    public List<PollResponse> getPollByGroupId(@CurrentUser UserPrincipal currentUser,
                                               @PathVariable Long id) {
        return pollService.getPollByGroup(id, currentUser);
    }

    @GetMapping("/group")
    public List<PollGroupResponse> getPollByGroup(@CurrentUser UserPrincipal currentUser) {
        return groupPollService.getPollByGroups(currentUser);
    }
}
