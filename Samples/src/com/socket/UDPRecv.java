package com.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPRecv {

    public static void main(String[] args) {
        try {
            //1、创建服务器端DatagramSocket，指定端口
            DatagramSocket socket =new DatagramSocket(10010);

            //2、创建数据报，用于接受客户端发送的数据
            byte[] data =new byte[1024];
            DatagramPacket packet =new DatagramPacket(data, data.length);

            //3、接受客户端发送的数据
            socket.receive(packet); //此方法在接受数据报之前会一致阻塞

            //4、读取数据
            String info =new String(data,0, data.length);
            System.out.println("I am Recv, UDPSend says:" + info);

            //=========================================================
            //向客户端响应数据
            //1、定义客户端的地址、端口号、数据
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            byte[] data2 = "Welcome!".getBytes();

            //2、创建数据报，包含响应的数据信息
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);

            //3、响应客户端
            socket.send(packet2);

            //4、关闭资源
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
