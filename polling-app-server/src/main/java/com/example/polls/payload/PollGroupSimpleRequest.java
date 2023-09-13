package com.example.polls.payload;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PollGroupSimpleRequest {
    private Integer id;

    @NotNull
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
