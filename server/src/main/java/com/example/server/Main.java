package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("连接成功");
                new Thread(new TCPServer(socket)).start();
            } catch (SocketTimeoutException e) {
                System.out.println("连接超时");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
