package com.pecodigos.zapweb.chats.service;

import com.pecodigos.zapweb.chats.dto.ChatRoomDTO;
import com.pecodigos.zapweb.chats.dto.mapper.ChatRoomMapper;
import com.pecodigos.zapweb.chats.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;
    private final ChatRoomRepository chatRoomRepository;

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
}
