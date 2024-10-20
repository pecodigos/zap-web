package com.pecodigos.zapweb.chats.dto.mapper;

import com.pecodigos.zapweb.chats.dto.MessageDTO;
import com.pecodigos.zapweb.chats.model.Message;
import com.pecodigos.zapweb.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper extends GenericMapper<Message, MessageDTO> {

    @Override
    public MessageDTO toDto(Message message) {
        if (message == null) {
            return null;
        }

        return new MessageDTO(message.getId() , message.getSender(), message.getContent(), message.getTimestamp());
    }

    @Override
    public Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        return Message.builder()
                .id(messageDTO.id())
                .sender(messageDTO.senderId())
                .content(messageDTO.content())
                .timestamp(messageDTO.timestamp())
                .build();
    }
}
