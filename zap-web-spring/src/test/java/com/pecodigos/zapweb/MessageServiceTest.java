package com.pecodigos.zapweb;

import com.pecodigos.zapweb.chats.dtos.MessageDTO;
import com.pecodigos.zapweb.chats.dtos.mappers.MessageMapper;
import com.pecodigos.zapweb.chats.models.ChatRoom;
import com.pecodigos.zapweb.chats.models.Message;
import com.pecodigos.zapweb.chats.repositories.MessageRepository;
import com.pecodigos.zapweb.chats.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAndReturnMessage() {
        UUID messageId = UUID.randomUUID();
        UUID senderId = UUID.randomUUID();
        UUID chatRoomId = UUID.randomUUID();
        MessageDTO messageDTO = new MessageDTO(messageId, senderId, chatRoomId, null, null);

        Message message = new Message();
        message.setId(messageId);
        message.setSenderId(senderId);
        message.setChatRoom(new ChatRoom(chatRoomId, new ArrayList<>(), null));
        message.setContent(null);
        message.setTimestamp(LocalDateTime.now());

        when(messageMapper.toEntity(any(MessageDTO.class))).thenReturn(message);
        when(messageMapper.toDto(any(Message.class))).thenReturn(messageDTO);

        when(messageRepository.save(any(Message.class))).thenReturn(message);

        MessageDTO savedMessageDTO = messageService.saveMessage(messageDTO);

        assertNotNull(savedMessageDTO);
        assertEquals(messageId, savedMessageDTO.id());
        assertEquals(senderId, savedMessageDTO.senderId());
        assertEquals(chatRoomId, savedMessageDTO.chatRoomId());
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenMessageNotFound() {
        UUID messageId = UUID.randomUUID();
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            messageService.getMessage(messageId);
        });
    }

    @Test
    void shouldDeleteMessage() {
        UUID messageId = UUID.randomUUID();
        when(messageRepository.existsById(messageId)).thenReturn(true);

        messageService.deleteMessage(messageId);

        verify(messageRepository).deleteById(messageId);
    }
}
