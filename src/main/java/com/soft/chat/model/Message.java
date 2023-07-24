package com.soft.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table
public class Message {
    @PrimaryKey
    private Long id;
    private Long senderId;
    private Long recipientId;
    private Long chatId;
    private String content;
    private LocalDateTime createdAt;
}
