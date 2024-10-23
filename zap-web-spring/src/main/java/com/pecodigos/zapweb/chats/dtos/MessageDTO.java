package com.pecodigos.zapweb.chats.dtos;

import com.pecodigos.zapweb.enums.Content;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTO(@NotNull UUID id, String text, UUID senderId, UUID chatRoomId, @NotNull Content content, @NotNull LocalDateTime timestamp) {
}
