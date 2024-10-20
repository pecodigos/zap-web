package com.pecodigos.zapweb.chats.dtos.mappers;

import com.pecodigos.zapweb.chats.dtos.MessageDTO;
import com.pecodigos.zapweb.chats.models.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageMapper {
    MessageDTO toDto(Message message);
    Message toEntity(MessageDTO messageDTO);
}
