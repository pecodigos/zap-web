package com.pecodigos.zapweb.chats.services;

import com.pecodigos.zapweb.chats.dtos.ChatRoomDTO;
import com.pecodigos.zapweb.chats.dtos.mappers.ChatRoomMapper;
import com.pecodigos.zapweb.chats.models.ChatRoom;
import com.pecodigos.zapweb.chats.repositories.ChatRoomRepository;
import com.pecodigos.zapweb.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final UserRepository userRepository;

    public ChatRoomDTO createOrFetchChat(UUID currentUserId, UUID otherUserId) {
        List<UUID> participantIds = Arrays.asList(currentUserId, otherUserId);
        Optional<ChatRoom> existingChat = chatRoomRepository.findChatRoomByParticipants(participantIds, participantIds.size());

        if (existingChat.isPresent()) {
            return chatRoomMapper.toDto(existingChat.get());
        } else {
            var newChatRoom = ChatRoom.builder()
                    .name("Chat with " + otherUserId)
                    .participants(userRepository.findAllById(participantIds))
                    .messages(new ArrayList<>())
                    .build();

            var savedChatRoom = chatRoomRepository.save(newChatRoom);
            return chatRoomMapper.toDto(savedChatRoom);
        }
    }

    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
        var chatRoom = chatRoomMapper.toEntity(chatRoomDTO);
        var savedChatRoom = chatRoomRepository.save(chatRoom);

        return chatRoomMapper.toDto(savedChatRoom);
    }

    public Optional<ChatRoomDTO> getChatRoom(UUID id) {
        return chatRoomRepository.findById(id)
                .map(chatRoomMapper::toDto);
    }

    public List<ChatRoomDTO> getAllChatRooms() {
        return chatRoomRepository.findAll()
                .stream().map(chatRoomMapper::toDto)
                .toList();
    }

    public void deleteChatRoom(UUID id) {
        if (!chatRoomRepository.existsById(id)) {
            throw new NoSuchElementException("No chat room with that id.");
        }

        chatRoomRepository.deleteById(id);
    }
}
