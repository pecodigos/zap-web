import { ChatService } from './chat.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Chat } from './chat.model';
import { WebSocketService } from '../web-socket/web-socket.service';
import { Message } from '../message/message.model';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatListModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
  newMessage: string = ''; // Variable to hold the input message
  activeChat: Chat | null = null; // The currently selected chat
  chats: Chat[] = [];
  userId: string = '';
  users: { id: string, name: string}[] = [];
  selectedUserId: string = '';

  constructor(private chatService: ChatService, private webSocketService: WebSocketService, private authService: AuthService) {}

  ngOnInit() {
    this.loadChats();
    this.getUserId();
    this.loadUsers();

    // Subscribe to WebSocket messages
    this.webSocketService.getMessages().subscribe((message: Message | null) => {
      if (message && this.activeChat && message.chatRoomId === this.activeChat.id) {
        this.activeChat.messages.push(message);
      }
    });
  }

  private loadUsers() {
    this.chatService.getUsers().subscribe(
      (users: { id: string, name: string }[]) => {
        this.users = users;
      },
      (error: any) => {
        console.log('Failed to load users:', error);
      }
    );
  }

  private getUserId() {
    this.authService.isLoggedIn().subscribe(isLoggedIn => {
      if (isLoggedIn) {
        this.userId = localStorage.getItem('userId') || '';
      }
    })
  }

  // Load the list of chat rooms
  loadChats() {
    this.chatService.getChats().subscribe(
      (chats: Chat[]) => {
        this.chats = chats;
      },
      (error: any) => {
        console.error('Failed to load chats:', error);
      }
    );
  }

  // Load messages for a specific chat room
  loadMessages(chat: Chat) {
    this.chatService.getMessages(chat.id).subscribe(
      (messages: Message[]) => {
        chat.messages = messages;
        this.setActiveChat(chat);
      },
      (error: any) => {
        console.error('Failed to load messages:', error);
      }
    );
  }

  // Send a new message to the active chat room
  sendMessage() {
    if (this.newMessage && this.activeChat) {
      const senderId = 'your-sender-id-here'; // Replace with actual sender ID

      this.chatService.sendMessage(this.activeChat.id, this.newMessage, senderId).subscribe(
        (response: Message) => {
          // Add the new message to the active chat
          this.activeChat?.messages.push(response);
          this.newMessage = '';
        },
        (error: any) => {
          console.error('Failed to send message:', error);
        }
      );
    }
  }

  // Set the active chat room
  setActiveChat(chat: Chat) {
    this.activeChat = chat;
  }

  // Start a new conversation (create a new chat room)
  startNewConversation() {
    if (!this.selectedUserId) {
      console.error('Please select a user to start a conversation.');
      return;
    }

    this.chatService.createOrFetchChatWithUser(this.selectedUserId).subscribe(
      (chat: Chat) => {
        this.chats.push(chat);
        this.setActiveChat(chat);
        this.loadMessages(chat);
      },
      (error: any) => {
        console.error('Failed to start conversation:', error);
      }
    )
  }
}
