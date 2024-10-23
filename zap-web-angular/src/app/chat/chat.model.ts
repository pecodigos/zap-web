import { Message } from "../message/message.model";

export interface Chat {
  id: string;
  name: string;
  avatar: string;
  lastMessage: string;
  messages: Message[];
}
