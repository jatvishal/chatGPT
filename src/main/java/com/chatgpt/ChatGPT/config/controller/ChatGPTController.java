package com.chatgpt.ChatGPT.config.controller;

import com.chatgpt.ChatGPT.config.dto.ChatGPTRequest;
import com.chatgpt.ChatGPT.config.dto.ChatGPTResponse;
import com.chatgpt.ChatGPT.config.dto.Message;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/openapi")
public class ChatGPTController {
    @Value("${chatgpt.model}")
    private String model;
    @Value("${chatgpt.url}")
    private String url;
    @Autowired
    private RestTemplate temp;
    @GetMapping("/chat")
    public String fetch(@RequestParam("value") String value){
        ChatGPTRequest request = new ChatGPTRequest(model, List.of(new Message("user",value)));
        ChatGPTResponse response = temp.postForObject(url,request, ChatGPTResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
