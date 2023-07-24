package com.soft.chat.controller;

import com.soft.chat.model.Message;
import com.soft.chat.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/stomp-broadcast")
    public String getWebSocketBroadcast() {
        return "stomp-broadcast";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message broadcastMessage(Message message) {
        return message;
    }
}