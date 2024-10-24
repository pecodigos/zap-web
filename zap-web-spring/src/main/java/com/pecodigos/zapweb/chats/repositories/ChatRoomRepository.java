package com.pecodigos.zapweb.chats.repositories;

import com.pecodigos.zapweb.chats.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {

    @Query("SELECT c FROM ChatRoom c JOIN c.participants p WHERE p.id IN :participantIds " +
            "GROUP BY c.id HAVING COUNT(p.id) = :size")
    Optional<ChatRoom> findChatRoomByParticipants(List<UUID> participantIds, long size);
}
