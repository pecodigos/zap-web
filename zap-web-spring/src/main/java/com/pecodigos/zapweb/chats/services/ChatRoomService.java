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
    private final ChatRoomMapper chatRoomMapper;

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
