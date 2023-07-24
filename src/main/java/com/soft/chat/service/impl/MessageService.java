package com.soft.chat.service.impl;

import com.soft.chat.repo.MessageRepository;
import com.soft.chat.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {
    @Autowired
    MessageRepository messageRepository;
}
