package com.pecodigos.zapweb.chats.repositories;

import com.pecodigos.zapweb.chats.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
}
