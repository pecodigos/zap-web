package com.pecodigos.zapweb.chats.dtos;

import com.pecodigos.zapweb.chats.models.Message;

import java.util.List;
import java.util.UUID;

public record ChatRoomDTO(UUID id, List<UUID> participantsId, List<Message> messages) {
}
