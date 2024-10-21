package com.pecodigos.zapweb.chats.dtos.mappers;

import com.pecodigos.zapweb.chats.dtos.ChatRoomDTO;
import com.pecodigos.zapweb.chats.models.ChatRoom;
import com.pecodigos.zapweb.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ChatRoomMapper {
    @Mapping(source = "participants", target = "participantsId", qualifiedByName = "mapUsersToUUIDs")
    ChatRoomDTO toDto(ChatRoom chatRoom);

    @Mapping(source = "participantsId", target = "participants", qualifiedByName = "mapUUIDsToUsers")
    ChatRoom toEntity(ChatRoomDTO chatRoomDTO);

    @Named("mapUsersToUUIDs")
    static List<UUID> mapUsersToUUIDs(List<User> users) {
        return users.stream()
                .map(User::getId)
                .toList();
    }

    @Named("mapUUIDsToUsers")
    static List<User> mapUUIDsToUsers(List<UUID> uuids) {
        return uuids.stream()
                .map(uuid -> User.builder()
                        .id(uuid)
                        .build())
                .toList();
    }
}
