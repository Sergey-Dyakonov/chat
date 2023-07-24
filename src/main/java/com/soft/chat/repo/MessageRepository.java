package com.soft.chat.repo;

import com.soft.chat.model.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface MessageRepository extends CassandraRepository<Message, UUID> {
}
