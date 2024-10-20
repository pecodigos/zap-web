package com.pecodigos.zapweb.chats.repository;

import com.pecodigos.zapweb.chats.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
