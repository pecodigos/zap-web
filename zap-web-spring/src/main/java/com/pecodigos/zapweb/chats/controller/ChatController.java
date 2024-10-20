package com.pecodigos.zapweb.chats.controller;

import com.pecodigos.zapweb.chats.dto.MessageDTO;
import com.pecodigos.zapweb.chats.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageDTO sendMessage(MessageDTO message) {
        return messageService.saveMessage(message);
    }
}
