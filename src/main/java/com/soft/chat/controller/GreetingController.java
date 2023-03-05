package com.soft.chat.controller;

import com.soft.chat.model.Message;
import com.soft.chat.model.User;
import lombok.SneakyThrows;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @SneakyThrows
    public Message greeting(User user) {
        return new Message("Hello, " + user.getName() + "!");
    }
}
