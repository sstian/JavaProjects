package com.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {

    public static void main(String[] args) {
        try {
            //1、定义服务器的地址、端口号、数据
            InetAddress address = InetAddress.getByName("localhost");
            int port =10010;
            byte[] data ="username：admin;pasword：123".getBytes();

            //2、创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

            //3、创建DatagramSocket对象
            DatagramSocket socket =new DatagramSocket();

            //4、向服务器发送数据
            socket.send(packet);


            //======================================
            //接受服务器端响应数据
            //1、创建数据报，用于接受服务器端响应数据
            byte[] data2 = new byte[1024];
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);

            //2、接受服务器响应的数据
            socket.receive(packet2);
            String reply = new String(data2, 0, packet2.getLength());
            System.out.println("I am Send, UDPRecv says：" + reply);

            //4、关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
