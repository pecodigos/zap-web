package com.pecodigos.zapweb.chats.controller;

import com.pecodigos.zapweb.chats.dtos.MessageDTO;
import com.pecodigos.zapweb.chats.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public void sendMessage(MessageDTO message) {
        var savedMessage = messageService.saveMessage(message);

        messagingTemplate.convertAndSend("/topic/chat-rooms/" + message.chatRoomId(), savedMessage);
    }

    @GetMapping("/")
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public List<MessageDTO> getMessageByChatRoom(@PathVariable(name = "id") UUID chatRoomId) {
        return messageService.getAllMessages()
                .stream()
                .filter(msg -> msg.id().equals(chatRoomId))
                .toList();
    }
}
