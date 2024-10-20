package com.pecodigos.zapweb.chats.dto;

import java.util.List;
import java.util.UUID;

public record ChatRoomDTO(UUID id, List<UUID> participantsId, List<UUID> messagesId) {
}
