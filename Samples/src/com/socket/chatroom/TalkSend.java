package com.socket.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable {
    DatagramSocket socket = null;
    BufferedReader reader = null;

    private int fromPort;
    private String toIP;
    private int toPort;

    public TalkSend(int fromPort, String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;

        try {
            System.out.println(">>> input your message:");
            this.socket = new DatagramSocket(fromPort);
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while(true) {
            try {
                String content = reader.readLine();
                byte[] data = content.getBytes();

                if (content.equals("bye")) {
                    break;
                }

                InetSocketAddress address = new InetSocketAddress(this.toIP, this.toPort);
                DatagramPacket packet = new DatagramPacket(data, 0, data.length, address);
                this.socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            this.socket.close();
            this.reader.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
