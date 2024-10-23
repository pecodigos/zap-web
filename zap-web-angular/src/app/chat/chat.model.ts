export interface Message {
  text: string;
  isSent: boolean;
}

export interface Chat {
  id: string;
  name: string;
  avatar: string;
  lastMessage: string;
  messages: Message[];
}
