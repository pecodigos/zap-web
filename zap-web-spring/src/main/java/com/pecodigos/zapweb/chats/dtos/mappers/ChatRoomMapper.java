package com.pecodigos.zapweb.chats.dtos.mappers;

import com.pecodigos.zapweb.chats.dtos.ChatRoomDTO;
import com.pecodigos.zapweb.chats.models.ChatRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ChatRoomMapper {
    @Mapping(source = "participants", target = "participantsId")
    ChatRoomDTO toDto(ChatRoom chatRoom);

    @Mapping(source = "participantsId", target = "participants")
    ChatRoom toEntity(ChatRoomDTO chatRoomDTO);
}
