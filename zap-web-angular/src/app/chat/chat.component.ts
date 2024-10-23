import { ChatService } from './chat.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Chat } from './chat.model';

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
  styleUrl: './chat.component.scss'
})
export class ChatComponent {
  newMessage: string = ''; // Variable to hold the input message
  activeChat: Chat | null = null; // The currently selected chat
  chats: Chat[] = [];

  constructor(private chatService: ChatService) {}

  ngOnInit() {
    this.loadChats();
  }

  loadChats() {
    this.chatService.getChats().subscribe(
      (chats: Chat[]) => {
        this.chats = chats;
      },
      (error: string) => {
        console.error('Failed to load chats:', error);
      }
    );
  }

  loadMessages(chat: any) {
    this.chatService.getMessages(chat.id).subscribe(
      (messages) => {
        chat.messages = messages;
        this.setActiveChat(chat);
      },
      (error) => {
        console.error('Failed to load messages:', error);
      }
    );
  }

  sendMessage() {
    if (this.newMessage && this.activeChat) {
      this.chatService.sendMessage(this.activeChat.id, this.newMessage).subscribe(
        (response) => {
          // Add the new message to the active chat
          this.activeChat?.messages.push({ text: this.newMessage, isSent: true });
          this.newMessage = '';
        },
        (error) => {
          console.error('Failed to send message:', error);
        }
      );
    }
  }

  setActiveChat(chat: any) {
    this.activeChat = chat;
  }

  startNewConversation() {
    const newChat: Chat = {
      id: (this.chats.length + 1).toString(),
      name: 'New User',
      avatar: 'N',
      lastMessage: '',
      messages: []
    }

    this.chats.push(newChat);
    this.setActiveChat(newChat);
  }
}
