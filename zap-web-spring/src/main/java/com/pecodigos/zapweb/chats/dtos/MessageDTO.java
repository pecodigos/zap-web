package com.pecodigos.zapweb.chats.dtos;

import com.pecodigos.zapweb.enums.ContentType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTO(@NotNull UUID id, String text, String imagePath, UUID senderId, UUID chatRoomId, @NotNull ContentType contentType, @NotNull LocalDateTime timestamp) {
}
