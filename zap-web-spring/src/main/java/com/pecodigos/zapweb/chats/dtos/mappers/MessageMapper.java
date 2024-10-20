package com.pecodigos.zapweb.chats.dtos.mappers;

import com.pecodigos.zapweb.chats.dtos.MessageDTO;
import com.pecodigos.zapweb.chats.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO toDto(Message message);
    Message toEntity(MessageDTO messageDTO);
    List<MessageDTO> toDtoList(List<Message> messages);
    List<Message> toEntityList(List<MessageDTO> messageDTOS);
}
