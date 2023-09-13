package com.example.polls.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PollGroupRequest {
    @NotNull
    private Integer groupId;

    private List<PollRequest> pollRequests;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<PollRequest> getPollRequests() {
        return pollRequests;
    }

    public void setPollRequests(List<PollRequest> pollRequests) {
        this.pollRequests = pollRequests;
    }
}
