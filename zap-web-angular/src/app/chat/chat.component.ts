import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

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
  activeChat: any = null; // The currently selected chat

  chats = [
    {
      name: 'Cabecinha',
      avatar: 'CB',
      lastMessage: 'Hey, there!',
      messages: [
        { text: 'Hello!', isSent: false },
        { text: 'How are you?', isSent: true }
      ]
    },
    {
      name: 'Zeco Digos',
      avatar: 'ZD',
      lastMessage: 'See you soon.',
      messages: [
        { text: 'When are you coming?', isSent: false },
        { text: 'Around 5 PM.', isSent: true }
      ]
    }
  ];

  sendMessage() {
    if (this.newMessage && this.activeChat) {
      this.activeChat.messages.push({ text: this.newMessage, isSent: true });
      this.newMessage = '';
    }
  }

  setActiveChat(chat: any) {
    this.activeChat = chat;
  }
}
