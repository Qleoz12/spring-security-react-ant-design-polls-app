package com.backend.ChatGPT;

public class DataClient {
    private String question;
    public DataClient() {}
    public DataClient(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
