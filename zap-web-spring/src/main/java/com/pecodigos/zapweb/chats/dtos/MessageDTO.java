package com.pecodigos.zapweb.chats.dtos;

import com.pecodigos.zapweb.enums.Content;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTO(UUID id, UUID senderId, Content content, LocalDateTime timestamp) {
}