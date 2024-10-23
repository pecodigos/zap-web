import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chat, Message } from './chat.model';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = 'http://localhost:8080/api/chats';
  constructor(private http: HttpClient) { }

  getChats(): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.apiUrl}`);
  }

  getMessages(chatId: string): Observable<any[]> {
    return this.http.get<Message[]>(`${this.apiUrl}/${chatId}/messages`);
  }

  sendMessage(chatId: string, message: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${chatId}/messages`, { text: message });
  }
}
