package com.pecodigos.zapweb.chats.service;

import com.pecodigos.zapweb.chats.dto.MessageDTO;
import com.pecodigos.zapweb.chats.dto.mapper.MessageMapper;
import com.pecodigos.zapweb.chats.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageDTO getChat(UUID id) {
        return messageRepository.findById(id)
                .map(messageMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("No chat found with that id."));
    }

    public MessageDTO saveMessage(MessageDTO messageDTO) {
        var chat = messageMapper.toEntity(messageDTO);
        messageRepository.save(chat);

        return messageMapper.toDto(chat);
    }
}
