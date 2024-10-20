package com.pecodigos.zapweb.chats.dtos.mappers;

import com.pecodigos.zapweb.chats.dtos.ChatRoomDTO;
import com.pecodigos.zapweb.chats.models.ChatRoom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatRoomMapper {
    ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

    ChatRoomDTO toDto(ChatRoom chatRoom);
    ChatRoom toEntity(ChatRoomDTO chatRoomDTO);
    List<ChatRoomDTO> toDtoList(List<ChatRoom> chatRooms);
    List<ChatRoom> toEntityList(List<ChatRoomDTO> chatRoomDTOS);
}
