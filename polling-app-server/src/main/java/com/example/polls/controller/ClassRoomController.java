package com.example.polls.controller;

import com.example.polls.model.classroom.Subject;
import com.example.polls.payload.*;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.ClassRoomService;
import com.example.polls.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/classroom")
public class ClassRoomController {

    @Autowired
    private ClassRoomService service;

    private static final Logger logger = LoggerFactory.getLogger(ClassRoomController.class);

//    @GetMapping
//    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
//                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return pollService.getAllPolls(currentUser, page, size);
//    }

    @PostMapping("/subjects")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> create(@Valid @RequestBody SubjectRequest request) {
        Subject subject = service.create(request);

       var resp=service.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(resp.getSubjectId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "subject Created Successfully"));
    }

    @GetMapping("/subjects/{id}")
    public SubjectResponse getById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long id) {
        return service.getById(id, currentUser);
    }

    @GetMapping("/subjects")
    public PagedResponse<Subject> getAll(@CurrentUser UserPrincipal currentUser,
                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return service.getAll(currentUser,page,size);
    }

}
