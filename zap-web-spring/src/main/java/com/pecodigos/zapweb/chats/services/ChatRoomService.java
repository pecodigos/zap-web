package com.pecodigos.zapweb.chats.services;

import com.pecodigos.zapweb.chats.dtos.ChatRoomDTO;
import com.pecodigos.zapweb.chats.dtos.mappers.ChatRoomMapper;
import com.pecodigos.zapweb.chats.repositories.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
        var chatRoom = ChatRoomMapper.INSTANCE.toEntity(chatRoomDTO);
        var savedChatRoom = chatRoomRepository.save(chatRoom);

        return ChatRoomMapper.INSTANCE.toDto(savedChatRoom);
    }

    public Optional<ChatRoomDTO> getChatRoom(UUID id) {
        return chatRoomRepository.findById(id)
                .map(ChatRoomMapper.INSTANCE::toDto);
    }

    public List<ChatRoomDTO> getAllChatRooms() {
        return chatRoomRepository.findAll()
                .stream().map(ChatRoomMapper.INSTANCE::toDto)
                .toList();
    }

    public void deleteChatRoom(UUID id) {
        if (!chatRoomRepository.existsById(id)) {
            throw new NoSuchElementException("No chat room with that id.");
        }

        chatRoomRepository.deleteById(id);
    }
}
