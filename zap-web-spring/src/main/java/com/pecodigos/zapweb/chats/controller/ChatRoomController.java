package com.pecodigos.zapweb.chats.controller;

import com.pecodigos.zapweb.chats.dtos.ChatRoomDTO;
import com.pecodigos.zapweb.chats.dtos.CreateChatRequest;
import com.pecodigos.zapweb.chats.services.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/chats")
@AllArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/create-or-fetch")
    public ResponseEntity<ChatRoomDTO> createOrFetchChat(@RequestBody CreateChatRequest request) {
        var chatRoomDTO = chatRoomService.createOrFetchChat(
                request.currentUserId(),
                request.otherUserId()
        );

        return ResponseEntity.ok(chatRoomDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ChatRoomDTO> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        var createdChatRoom = chatRoomService.createChatRoom(chatRoomDTO);
        return ResponseEntity.ok(createdChatRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomDTO> getChatRoom(@PathVariable(name = "id") UUID id) {
        var chatRoomDTO = chatRoomService.getChatRoom(id).orElseThrow(() -> new NoSuchElementException("No ChatRoom with that id."));
        return ResponseEntity.ok(chatRoomDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<ChatRoomDTO>> getAllChatRooms() {
        var chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable(name = "id") UUID id) {
        chatRoomService.deleteChatRoom(id);
        return ResponseEntity.noContent().build();
    }
}
