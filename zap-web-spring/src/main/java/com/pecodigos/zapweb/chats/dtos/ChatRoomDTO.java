package com.pecodigos.zapweb.chats.dtos;

import java.util.List;
import java.util.UUID;

public record ChatRoomDTO(UUID id, List<UUID> participantsId, List<UUID> messagesId) {
}
