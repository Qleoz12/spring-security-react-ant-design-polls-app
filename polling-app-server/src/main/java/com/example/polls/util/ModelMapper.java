package com.example.polls.util;

import com.example.polls.model.Poll;
import com.example.polls.model.PollGroup;
import com.example.polls.model.User;
import com.example.polls.model.classroom.Subject;
import com.example.polls.model.classroom.SubjectPollProfessor;
import com.example.polls.payload.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        Instant now = Instant.now();
        pollResponse.setExpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());

            if(choiceVotesMap.containsKey(choice.getId())) {
                choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
            } else {
                choiceResponse.setVoteCount(0);
            }
            return choiceResponse;
        }).collect(Collectors.toList());

        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreatedBy(creatorSummary);

        if(userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }


    public static PollResponse mapPollToPollResponseSimple(Poll poll) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        pollResponse.setGroupid(poll.getPollGroup().getId());
        Instant now = Instant.now();
        pollResponse.setExpired(poll.getExpirationDateTime().isBefore(now));

        return pollResponse;
    }

    public static SubjectResponse toResponse(Subject subject) {
        SubjectResponse subjectResponse= new SubjectResponse();
        subjectResponse.setSubjectName(subject.getSubjectName());
        subjectResponse.setDepartment(subject.getDepartment());
        return subjectResponse;
    }

    public static PollGroupResponse toResponse(PollGroup pollGroup) {
        PollGroupResponse response= new PollGroupResponse();
        response.setId(pollGroup.getId());
        response.setName(pollGroup.getName());

        return response;
    }

    public static SubjectPollProfessorResponse toResponse(SubjectPollProfessor subjectPollProfessor) {
        SubjectPollProfessorResponse response= new SubjectPollProfessorResponse();
        response.setTeacherId(subjectPollProfessor.getTeacher().getId());
        response.setSubjectId(subjectPollProfessor.getSubject().getSubjectId());
        response.setPollGroupId(subjectPollProfessor.getPollGroup().getId());
        response.setSubjectPollProfessorId(subjectPollProfessor.getSubjectPollProfessorId());
        return response;
    }
}
