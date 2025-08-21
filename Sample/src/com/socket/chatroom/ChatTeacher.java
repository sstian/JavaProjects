package com.socket.chatroom;

public class ChatTeacher {
    public static void main(String[] args) {
        new Thread(new TalkSend(9999, "localhost", 6666)).start();
        new Thread(new TalkReceive(7777, "学生")).start();
    }
}
