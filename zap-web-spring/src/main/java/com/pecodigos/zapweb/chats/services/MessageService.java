package com.pecodigos.zapweb.chats.services;

import com.pecodigos.zapweb.chats.dtos.MessageDTO;
import com.pecodigos.zapweb.chats.dtos.mappers.MessageMapper;
import com.pecodigos.zapweb.chats.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageDTO getChat(UUID id) {
        return messageRepository.findById(id)
                .map(MessageMapper.INSTANCE::toDto)
                .orElseThrow(() -> new NoSuchElementException("No chat found with that id."));
    }

    public MessageDTO saveMessage(MessageDTO messageDTO) {
        var chat = MessageMapper.INSTANCE.toEntity(messageDTO);
        messageRepository.save(chat);

        return MessageMapper.INSTANCE.toDto(chat);
    }

    public void deleteMessage(UUID id) {
        if (!messageRepository.existsById(id)) {
            throw new NoSuchElementException("No message with that id.");
        }

        messageRepository.deleteById(id);
    }
}
