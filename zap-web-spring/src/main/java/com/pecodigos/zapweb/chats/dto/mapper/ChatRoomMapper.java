package com.pecodigos.zapweb.chats.dto.mapper;

import com.pecodigos.zapweb.chats.dto.ChatRoomDTO;
import com.pecodigos.zapweb.chats.model.ChatRoom;
import com.pecodigos.zapweb.chats.model.Message;
import com.pecodigos.zapweb.mapper.GenericMapper;
import com.pecodigos.zapweb.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomMapper extends GenericMapper<ChatRoom, ChatRoomDTO> {

    @Override
    public ChatRoomDTO toDto(ChatRoom entity) {
        if (entity == null) {
            return null;
        }
        var participantsId = entity.getParticipants()
                .stream().map(User::getId).toList();

        var messagesIds = entity.getMessages()
                .stream().map(Message::getId).toList();

        return new ChatRoomDTO(entity.getId(), participantsId, messagesIds);
    }

    @Override
    public ChatRoom toEntity(ChatRoomDTO dto) {
        if (dto == null) {
            return null;
        }

        return ChatRoom.builder()
                .id(dto.id())
                .build();
    }
}
