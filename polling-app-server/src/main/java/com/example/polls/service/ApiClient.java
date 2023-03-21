package com.example.polls.service;

import com.example.polls.payload.ApiIArequest;
import com.example.polls.provider.IAResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${client.ia}")
public interface ApiClient {

     @PostMapping(value = "/chatGPT")
     IAResponse generatePoll(@RequestBody ApiIArequest request);

}