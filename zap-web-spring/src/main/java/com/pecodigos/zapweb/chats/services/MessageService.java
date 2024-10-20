package com.pecodigos.zapweb.chats.services;

import com.pecodigos.zapweb.chats.dtos.MessageDTO;
import com.pecodigos.zapweb.chats.dtos.mappers.MessageMapper;
import com.pecodigos.zapweb.chats.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageDTO getMessage(UUID id) {
        return messageRepository.findById(id)
                .map(messageMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("No chat found with that id."));
    }

    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll()
                .stream().map(messageMapper::toDto)
                .toList();
    }

    public MessageDTO saveMessage(MessageDTO messageDTO) {
        var chat = messageMapper.toEntity(messageDTO);
        messageRepository.save(chat);

        return messageMapper.toDto(chat);
    }

    public void deleteMessage(UUID id) {
        if (!messageRepository.existsById(id)) {
            throw new NoSuchElementException("No message with that id.");
        }

        messageRepository.deleteById(id);
    }
}