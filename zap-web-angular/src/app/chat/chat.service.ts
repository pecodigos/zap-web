import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chat } from './chat.model';
import { Message } from '../message/message.model';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private chatApiUrl = 'http://localhost:8080/api/chats';
  private messageApiUrl = 'http://localhost:8080/api/messages';

  constructor(private http: HttpClient) {}

  // Get all chat rooms
  getChats(): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.chatApiUrl}/`);
  }

  // Get messages for a specific chat room by ID
  getMessages(chatId: string): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.messageApiUrl}/${chatId}`);
  }

  // Send a message to a specific chat room
  sendMessage(chatId: string, message: string, senderId: string): Observable<Message> {
    const messagePayload = {
      senderId: senderId,
      chatRoomId: chatId,
      content: message,
      timestamp: new Date()
    };
    return this.http.post<Message>(`${this.messageApiUrl}/`, messagePayload);
  }

  getUsers(): Observable<{ id: string, name: string }[]> {
    return this.http.get<{ id: string, name: string }[]>('http://localhost:8080/api/users');
  }

  createOrFetchChatWithUser(userId: string): Observable<Chat> {
    return this.http.post<Chat>(`${this.chatApiUrl}/with-user`, { userId });
  }
}
