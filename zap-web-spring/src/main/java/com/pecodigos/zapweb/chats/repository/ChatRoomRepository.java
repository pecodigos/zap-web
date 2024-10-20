package com.pecodigos.zapweb.chats.repository;

import com.pecodigos.zapweb.chats.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
}
