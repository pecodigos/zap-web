package com.pecodigos.zapweb.chats.models;

import com.pecodigos.zapweb.users.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chatrooms")
public class ChatRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany(mappedBy = "chatRooms")
    private List<User> participants;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Message> messages;
}
