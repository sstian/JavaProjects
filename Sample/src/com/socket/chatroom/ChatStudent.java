package com.socket.chatroom;

public class ChatStudent {
    public static void main(String[] args) {
        new Thread(new TalkSend(5555, "localhost", 7777)).start();
        new Thread(new TalkReceive(6666, "老师")).start();
    }
}
