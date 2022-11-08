package com.example.polls.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class RankingResponse {


    private int totalVotes;
    private int pollsSucces;
    private int pollsFailed;

    List<RankingData> votesGrouped;


    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public int getPollsSucces() {
        return pollsSucces;
    }

    public void setPollsSucces(int pollsSucces) {
        this.pollsSucces = pollsSucces;
    }

    public int getPollsFailed() {
        return pollsFailed;
    }

    public void setPollsFailed(int pollsFailed) {
        this.pollsFailed = pollsFailed;
    }

    public List<RankingData> getVotesGrouped() {
        return votesGrouped;
    }

    public void setVotesGrouped(List<RankingData> votesGrouped) {
        this.votesGrouped = votesGrouped;
    }
}
