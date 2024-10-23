import { Injectable } from '@angular/core';
import { Client, IMessage, IStompSocket } from '@stomp/stompjs';
import { BehaviorSubject, Observable } from 'rxjs';
import { Message } from '../message/message.model';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: Client;
  private messageSubject = new BehaviorSubject<Message | null>(null);

  constructor() {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/chat',
      connectHeaders: {},
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    // WebSocket factory using SockJS
    this.stompClient.webSocketFactory = () => new SockJS('http://localhost:8080/chat') as IStompSocket;

    // Subscribe to topics on connection
    this.stompClient.onConnect = () => {
      console.log('Connected to WebSocket server');
      this.stompClient.subscribe('/topic/chat-rooms/*', (message: IMessage) => {
        this.onMessageReceived(message);
      });
    };

    // Activate the STOMP client
    this.stompClient.activate();
  }

  // Handle incoming WebSocket messages
  private onMessageReceived(message: IMessage) {
    const msg: Message = JSON.parse(message.body);
    this.messageSubject.next(msg);
  }

  // Observable to get messages
  getMessages(): Observable<Message | null> {
    return this.messageSubject.asObservable();
  }

  // Send a message through the WebSocket
  sendMessage(message: Message) {
    this.stompClient.publish({
      destination: '/app/sendMessage',
      body: JSON.stringify(message),
    });
  }
}
